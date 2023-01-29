package com.example.algafood.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.example.algafood.domain.model.Restaurante;
import com.example.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    RestauranteService restauranteService;

    @GetMapping
    public List<Restaurante> listarRestaurantes(){
      return restauranteService.listarRetaurantes();
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
    public ResponseEntity<?> salvarRestaurante(@RequestBody Restaurante restaurante){
      try{
        Restaurante restaurante2 = restauranteService.salvarRestaurante(restaurante);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurante2);
      }catch(EntidadeNaoEncontradaException e){
        return ResponseEntity.badRequest().body(e.getMessage());
      }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> alterarRestaurante(@PathVariable(value = "id") long id, @RequestBody Restaurante restaurante){
        Restaurante restaurante2 = restauranteService.alterarRestaurante(id, restaurante);
        if(restaurante2!=null){
         return ResponseEntity.ok(restaurante2);
        }
        return ResponseEntity.notFound().build();
    }
    
}