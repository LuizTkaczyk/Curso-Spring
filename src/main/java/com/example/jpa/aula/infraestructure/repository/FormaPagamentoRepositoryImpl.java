package com.example.jpa.aula.infraestructure.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.jpa.aula.domain.model.FormaPagamento;
import com.example.jpa.aula.domain.repository.FormaPagamentoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class FormaPagamentoRepositoryImpl implements  FormaPagamentoRepository {

     @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<FormaPagamento> listar() {
         return entityManager.createQuery("from FormaPagamento", FormaPagamento.class).getResultList();
    }

    @Override
    public FormaPagamento buscar(Long id) {
        return entityManager.find(FormaPagamento.class, id);
    }

    @Override
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return entityManager.merge(formaPagamento);
    }

    @Override
    public void remover(FormaPagamento formaPagamento) {
        formaPagamento = buscar(formaPagamento.getId());
        entityManager.remove(formaPagamento);
    }

}
