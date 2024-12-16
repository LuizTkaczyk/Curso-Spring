package com.example.jpa.aula.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.aula.domain.exception.EntidadeEmUsoException;
import com.example.jpa.aula.domain.exception.EntidadeNaoEncontradaException;
import com.example.jpa.aula.domain.model.Cozinha;
import com.example.jpa.aula.domain.repository.CozinhaRepository;
import com.example.jpa.aula.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    // public Cozinha buscar(@PathVariable(value = "id") Long id) { //OU
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
        Optional<Cozinha> cozinha = cozinhaRepository.findById(id);

        if (cozinha.isPresent()) {
            return ResponseEntity.ok(cozinha.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {
        return cadastroCozinhaService.salvar(cozinha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
        Optional <Cozinha> cozinhaSalva = cozinhaRepository.findById(id);

        if(cozinhaSalva.isPresent()){
            BeanUtils.copyProperties(cozinha, cozinhaSalva.get(), "id");

            Cozinha cozinhaAtualizada = cadastroCozinhaService.salvar(cozinhaSalva.get());

            return ResponseEntity.ok(cozinhaAtualizada);
        } else {
            return ResponseEntity.notFound().build();   
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Cozinha> remover(@PathVariable Long id) {
        try {
            cadastroCozinhaService.excluir(id);

            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/por-nome")
    public List<Cozinha> buscarPorNome(String nome) {
        return cozinhaRepository.findTodasByNomeContaining(nome);
    }

}
