package com.example.jpa.aula.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.jpa.aula.domain.model.Cozinha;

@Repository
public interface CozinhaRepository {

    List<Cozinha> listar();

    Cozinha buscar(Long id);

    Cozinha salvar(Cozinha cozinha);

    void remover(Long id);
}
