package com.example.jpa.aula.domain.repository;

import org.springframework.stereotype.Repository;

import com.example.jpa.aula.domain.model.Permissao;

@Repository
public interface PermissaoRepository extends CustomJpaRepository<Permissao, Long> {
}
