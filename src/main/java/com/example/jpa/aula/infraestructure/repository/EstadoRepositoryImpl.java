package com.example.jpa.aula.infraestructure.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.jpa.aula.domain.model.Estado;
import com.example.jpa.aula.domain.repository.EstadoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Estado> listar() {
       return entityManager.createQuery("from Estado", Estado.class).getResultList();
    }

    @Override
    public Estado buscar(Long id) {
         return entityManager.find(Estado.class, id);
    }

    @Override
    public Estado salvar(Estado estado) {
        return entityManager.merge(estado);
    }

    @Override
    public void remover(Estado estado) {
        estado = buscar(estado.getId());
        entityManager.remove(estado);
    }

}
