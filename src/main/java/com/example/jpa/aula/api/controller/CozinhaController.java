package com.example.jpa.aula.api.controller;

import java.util.List;

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
        return cozinhaRepository.listar();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    // public Cozinha buscar(@PathVariable(value = "id") Long id) { //OU
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
        Cozinha cozinha = cozinhaRepository.buscar(id);

        if (cozinha == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(cozinha);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {
        return cadastroCozinhaService.salvar(cozinha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
        Cozinha cozinhaSalva = cozinhaRepository.buscar(id);

        if (cozinhaSalva == null) { // nao encontrou
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(cozinha, cozinhaSalva, "id");

        cadastroCozinhaService.salvar(cozinhaSalva);

        return ResponseEntity.ok(cozinhaSalva);
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

}
