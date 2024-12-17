package com.example.jpa.aula.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.jpa.aula.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long>{

    List<Cozinha> findTodasByNomeContaining(String nome);
}
