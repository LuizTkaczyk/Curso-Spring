package com.example.jpa.aula.api.controller;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.aula.domain.exception.EntidadeNaoEncontradaException;
import com.example.jpa.aula.domain.model.Restaurante;
import com.example.jpa.aula.domain.repository.RestauranteRepository;
import com.example.jpa.aula.domain.service.CadastroRestauranteService;
import static com.example.jpa.aula.infraestructure.spec.RestauranteSpecs.comFreteGratis;
import static com.example.jpa.aula.infraestructure.spec.RestauranteSpecs.comNomeSemelhante;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
       Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);

        if (restaurante.isPresent()) {
            return ResponseEntity.ok(restaurante.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        try {
            restaurante = cadastroRestaurante.salvar(restaurante);

            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        Optional<Restaurante> restauranteSalvo = restauranteRepository.findById(id);

        if (restauranteSalvo.isEmpty()) { // nao encontrou
            return ResponseEntity.notFound().build();
        }

        try {
            BeanUtils.copyProperties(restaurante, restauranteSalvo, "id");

            cadastroRestaurante.salvar(restauranteSalvo.get());

            return ResponseEntity.ok(restauranteSalvo);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {

        Optional<Restaurante> restauranteSalvo = restauranteRepository.findById(id);

        if (restauranteSalvo.isEmpty()) { // nao encontrou
            return ResponseEntity.notFound().build();
        }

        merge(campos, restauranteSalvo.get());

        return atualizar(id, restauranteSalvo.get());
    }

    private void merge(Map<String, Object> campos, Restaurante restauranteSalvo) {

        ObjectMapper mapper = new ObjectMapper();

        Restaurante restauranteOrigem = mapper.convertValue(campos, Restaurante.class);

        campos.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, key);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

            ReflectionUtils.setField(field, restauranteSalvo, novoValor);

        });
    }

    @GetMapping("/por-taxa")
    public List<Restaurante> restaurantesPorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return restauranteRepository.findByTaxaFreteBetween(taxaInicial,taxaFinal);
    }

    @GetMapping("/por-nome-e-cozinha")
    public List<Restaurante> restaurantesPorNomeAndCozinha(String nome, Long cozinhaId) {
        return restauranteRepository.findByNomeContainingAndCozinhaId(nome,cozinhaId);
    }

    @GetMapping("/primeiro")
    public Optional<Restaurante> restaurantesPrimeiro(String nome) {
        return restauranteRepository.findFirstByNomeContaining(nome);
    }

    @GetMapping("/top2-por-nome")
    public List<Restaurante> restaurantesTop2(String nome) {
        return restauranteRepository.findTop2ByNomeContaining(nome);
    }

    @GetMapping("/existe-nome")
    public boolean restaurantesExiste(String nome) {
        return restauranteRepository.existsByNome(nome);
    }

    @GetMapping("/quantas-cozinhas")
    public int restaurantesQuantasCozinhas(Long cozinhaId) {
        return restauranteRepository.countByCozinhaId(cozinhaId);
    }

    @GetMapping("/por-nome")
    public List<Restaurante> restaurantesPorNome(String restaurante, Long cozinhaId) {
        return restauranteRepository.consultarPorNome(restaurante, cozinhaId);
    }

    @GetMapping("/por-nome-frete")
    public List<Restaurante> restaurantesPorNomeFrete(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
    }

    @GetMapping("/com-frete-gratis")
    public List<Restaurante> restaurantesComFrteGrais(String nome) {
        return restauranteRepository.findAll(comFreteGratis().and(comNomeSemelhante(nome)));
    }
    
    @GetMapping("/primeiro-nome")
    public Optional<Restaurante> restaurantePrimeiro() {
        return restauranteRepository.buscarPrimeiro();
    }

}
