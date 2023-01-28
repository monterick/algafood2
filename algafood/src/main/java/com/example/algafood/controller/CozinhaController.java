package com.example.algafood.controller;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.NotFound;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algafood.domain.exceptions.EntidadeEmUsoException;
import com.example.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.example.algafood.domain.model.Cozinha;
import com.example.algafood.domain.service.CozinhaService;
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
    
    @Autowired
    CozinhaService cozinhaService;

    @GetMapping
    public List<Cozinha> listarCozinhas(){       
       return cozinhaService.listarCozinhas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscarCozinha(@PathVariable(value = "id") long id){
      Optional<Cozinha> cozinha = cozinhaService.buscarCozinha(id);
      if(cozinha.isPresent()){
        return ResponseEntity.ok(cozinha.get());
      }
      return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha salvarCozinha(@RequestBody Cozinha cozinha){
         return cozinhaService.salvarCozinha(cozinha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> alterarCozinha(@PathVariable(value = "id") long id, @RequestBody Cozinha cozinha){
       Cozinha cozinhaAtual = cozinhaService.alterarCozinha(id, cozinha);
       if(cozinhaAtual!=null){
         return ResponseEntity.ok(cozinhaAtual);
       }
       return ResponseEntity.notFound().build(); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerCozinha(@PathVariable(value = "id") long id){
      try{  
       cozinhaService.removerCozinha(id);
       return ResponseEntity.noContent().build();
      }catch(EntidadeNaoEncontradaException e){
       return ResponseEntity.notFound().build();
      }catch(EntidadeEmUsoException e){
       return ResponseEntity.badRequest().body(e.getMessage());
      }
    }

}
