package com.example.jpa.aula.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.jpa.aula.domain.exception.EntidadeEmUsoException;
import com.example.jpa.aula.domain.exception.EntidadeNaoEncontradaException;
import com.example.jpa.aula.domain.model.Estado;
import com.example.jpa.aula.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

    @Autowired
    private EstadoRepository estadoRepository;
    
    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void remover(Long id) {
        try {
            estadoRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Cidade de código %d não encontrada", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Estado de código %d não pode ser removida, pois está em uso", id));
        }
    }

}
