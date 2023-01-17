package com.example.algafood;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.algafood.domain.model.Restaurante;
import com.example.algafood.domain.services.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    RestauranteService restauranteService;

    @GetMapping
    public List<Restaurante> listarRestaurantes() {
        return restauranteService.listarRestaurantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscarRestaurante(@PathVariable(value = "id") long id) {
        Optional<Restaurante> restaurante = restauranteService.buscarRestaurante(id);
        if (restaurante.isPresent()) {
            return ResponseEntity.ok(restaurante.get());
        }
        return ResponseEntity.notFound().build();
    }

}
