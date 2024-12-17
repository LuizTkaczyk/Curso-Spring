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
import com.example.jpa.aula.domain.model.Estado;
import com.example.jpa.aula.domain.repository.EstadoRepository;
import com.example.jpa.aula.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    @GetMapping
    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<?> buscar(@PathVariable Long estadoId) {
        Optional<Estado> estado = estadoRepository.findById(estadoId);

        if (estado.isPresent()) {
            return ResponseEntity.ok(estado.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Estado estado) {
        estado = cadastroEstadoService.salvar(estado);
        return ResponseEntity.status(HttpStatus.CREATED).body(estado);

    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<?> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
        Optional<Estado> estadoSalvo = estadoRepository.findById(estadoId);

        if (estadoSalvo.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(estado, estadoSalvo.get(), "id");

        cadastroEstadoService.salvar(estadoSalvo.get());

        return ResponseEntity.ok(estadoSalvo);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Estado> remover(@PathVariable Long id) {
        try {
            cadastroEstadoService.remover(id);

            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
