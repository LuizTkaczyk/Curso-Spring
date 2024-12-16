package com.example.jpa.aula.domain.repository;

import org.springframework.stereotype.Repository;

import com.example.jpa.aula.domain.model.Cidade;

@Repository
public interface CidadeRepository extends CustomJpaRepository<Cidade, Long> {

}
