package com.example.jpa.aula;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.jpa.aula.infraestructure.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AulaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AulaApplication.class, args);
	}

}
