package com.algaworks.algafood.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.RestauranteService;
import com.algaworks.algafood.exceptions.EntidadeNaoEncontradaException;




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
    public ResponseEntity<?> salvarRestaurante(@RequestBody Restaurante restaurante){
      try{
       Restaurante restaurante2 = restauranteService.salvarRestaurante(restaurante); 
       return ResponseEntity.status(HttpStatus.CREATED).body(restaurante2);
      }catch(EntidadeNaoEncontradaException e){
        return ResponseEntity.badRequest().body(e.getMessage());
      }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> alterarRestaurante(@PathVariable(value = "id") long id, @RequestBody Restaurante restaurante){
     try{
       Restaurante restaurante2 = restauranteService.alterarRestaurante(id, restaurante); 
       if(restaurante2 == null){
        return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(restaurante2);   
     }catch(EntidadeNaoEncontradaException e){
       return ResponseEntity.badRequest().body(e.getMessage());
     }  

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerRestaurante(@PathVariable(value="id") long id){
      try{
        restauranteService.removerRestaurante(id);
        return ResponseEntity.noContent().build();
      }catch(EntidadeNaoEncontradaException e){
        return ResponseEntity.notFound().build(); 
      }
    }
    

}
