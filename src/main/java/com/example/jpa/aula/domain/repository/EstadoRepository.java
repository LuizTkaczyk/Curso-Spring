package com.example.jpa.aula.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.jpa.aula.domain.model.Estado;

@Repository
public interface EstadoRepository {
    List<Estado> listar();

    Estado buscar(Long id);

    Estado salvar(Estado estado);

    void remover(Long id);
}
