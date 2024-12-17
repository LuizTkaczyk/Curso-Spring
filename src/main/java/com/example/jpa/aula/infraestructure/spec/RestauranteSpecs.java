package com.example.jpa.aula.infraestructure.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.example.jpa.aula.domain.model.Restaurante;

public class RestauranteSpecs {

    public static Specification<Restaurante> comFreteGratis(){
        return (root, criteriaQuery, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
        
    }

    public static Specification<Restaurante> comNomeSemelhante(String nome){
        return (root, criteriaQuery, criteriaBuilder) -> 
            criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
    }
}
