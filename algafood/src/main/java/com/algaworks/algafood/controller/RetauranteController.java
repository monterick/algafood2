package com.algaworks.algafood.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.RestauranteService;



@RestController
@RequestMapping("/restaurantes")
public class RetauranteController {
    
    @Autowired
    RestauranteService restauranteService;

    @GetMapping
    public List<Restaurante> listarRestaurantes(){
       return restauranteService.listarRestaurantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscarRestaurante(@PathVariable(value = "id") long id){
        Optional<Restaurante> restaurante = restauranteService.buscarRestaurante(id);
        if(restaurante.isPresent()){
          return ResponseEntity.ok(restaurante.get());
        } 
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public Restaurante salvarRestaurante(@RequestBody Restaurante restaurante){
       return restauranteService.salvarRestaurante(restaurante);
    }
    

}
