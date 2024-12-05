package com.example.jpa.aula.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jpa.aula.domain.exception.EntidadeNaoEncontradaException;
import com.example.jpa.aula.domain.model.Cozinha;
import com.example.jpa.aula.domain.model.Restaurante;
import com.example.jpa.aula.domain.repository.CozinhaRepository;
import com.example.jpa.aula.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante){
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

        System.out.println(cozinha);

        if(cozinha == null){
            throw new EntidadeNaoEncontradaException(String.format("Cozinha de código %d nao encontrada", cozinhaId));
        }

        restaurante.setCozinha(cozinha);

        return restauranteRepository.salvar(restaurante);
    }
}
