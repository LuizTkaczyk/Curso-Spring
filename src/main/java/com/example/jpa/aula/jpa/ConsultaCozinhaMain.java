package com.example.jpa.aula.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.example.jpa.aula.AulaApplication;
import com.example.jpa.aula.domain.model.Cozinha;
import com.example.jpa.aula.domain.repository.CozinhaRepository;

public class ConsultaCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AulaApplication.class).web(WebApplicationType.NONE).run(args);

        CozinhaRepository cadastroCozinha = applicationContext.getBean(CozinhaRepository.class);
       
        List<Cozinha> cozinhas = cadastroCozinha.listar();

        for(Cozinha cozinha: cozinhas){
            System.out.println(cozinha.getNome());
        }

        // Cozinha cozinha1 = new Cozinha();
        // cozinha1.setNome("Brasileira");

        // Cozinha cozinha2 = new Cozinha();
        // cozinha2.setNome("Japonesa");

        // cadastroCozinha.adicionar(cozinha1);
        // cadastroCozinha.adicionar(cozinha2);


        // Cozinha cozinha = cadastroCozinha.buscar(2L);
        // System.out.println(cozinha.getNome());


        // Cozinha cozinha = new Cozinha();
        // cozinha.setId(1L);
        // cozinha.setNome("Bucetuda");

        // cadastroCozinha.adicionar(cozinha);


        // Cozinha cozinha = new Cozinha();
        // cozinha.setId(1L);
        // cadastroCozinha.remover(cozinha);

       
    }

}
