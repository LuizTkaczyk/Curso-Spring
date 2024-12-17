package com.example.jpa.aula.domain.repository;

import org.springframework.stereotype.Repository;

import com.example.jpa.aula.domain.model.FormaPagamento;

@Repository
public interface FormaPagamentoRepository extends CustomJpaRepository<FormaPagamento, Long> {
}
