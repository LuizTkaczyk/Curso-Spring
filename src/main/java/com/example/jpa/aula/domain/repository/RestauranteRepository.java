package com.example.jpa.aula.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.jpa.aula.domain.model.Restaurante;

@Repository
public interface RestauranteRepository {
    List<Restaurante> listar();

    Restaurante buscar(Long id);

    Restaurante salvar(Restaurante restaurante);

    void remover(Restaurante restaurante);
}
