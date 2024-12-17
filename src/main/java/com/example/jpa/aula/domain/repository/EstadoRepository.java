package com.example.jpa.aula.domain.repository;

import org.springframework.stereotype.Repository;

import com.example.jpa.aula.domain.model.Estado;

@Repository
public interface EstadoRepository extends CustomJpaRepository<Estado, Long> {
}
