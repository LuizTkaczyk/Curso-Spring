package com.example.jpa.aula.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.aula.domain.model.Cozinha;
import com.example.jpa.aula.domain.repository.CozinhaRepository;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }

    @GetMapping("/{id}")
    //public Cozinha buscar(@PathVariable(value = "id") Long id) { // @PathVariable é um recurso do Spring
    public Cozinha buscar(@PathVariable Long id) {
        return cozinhaRepository.buscar(id);
    }
}
