package com.example.jpa.aula.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.jpa.aula.domain.exception.EntidadeEmUsoException;
import com.example.jpa.aula.domain.exception.EntidadeNaoEncontradaException;
import com.example.jpa.aula.domain.model.Cidade;
import com.example.jpa.aula.domain.model.Estado;
import com.example.jpa.aula.domain.repository.CidadeRepository;
import com.example.jpa.aula.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {
    
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoRepository.buscar(estadoId);

        if(estado == null) {
            throw new EntidadeNaoEncontradaException(String.format("Estdo de código %d nao encontrado", estadoId));
        }

        cidade.setEstado(estado);

        return cidadeRepository.salvar(cidade);
    }

    public void remover(Long id) {
        try {
			cidadeRepository.remover(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
				String.format("Não existe um cadastro de cidade com código %d", id));
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("Cidade de código %d não pode ser removida, pois está em uso", id));
		}
    }
}
