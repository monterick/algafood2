package com.example.algafood.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.algafood.domain.model.Restaurante;
import com.example.algafood.domain2.repository.RestauranteRepositoryImpl;



@RestController
@RequestMapping("/restaurantes2")
public class RestauranteController2 {

    @Autowired
    RestauranteRepositoryImpl restauranteRepository;

    @GetMapping
    public List<Restaurante> find(@RequestParam(value = "nome") String nome, 
           @RequestParam(value = "tx_inicial") BigDecimal taxaInicial, @RequestParam (value = "tx_final")BigDecimal taxaFinal){
      return restauranteRepository.find(nome, taxaInicial, taxaFinal);
    }
    
}
