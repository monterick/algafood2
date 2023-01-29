package com.example.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.example.algafood.domain.model.Cozinha;
import com.example.algafood.domain.model.Restaurante;
import com.example.algafood.domain.repository.CozinhaRepository;
import com.example.algafood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {
    @Autowired
    RestauranteRepository restauranteRepository;

    @Autowired
    CozinhaRepository cozinhaRepository;

    public List<Restaurante> listarRetaurantes(){
        return restauranteRepository.findAll(); 
    }

    public Optional<Restaurante> buscarRestaurante(long id){
        Optional<Restaurante> restaurante = restauranteRepository.findById(id);
        return restaurante;
    }

    public Restaurante salvarRestaurante(Restaurante restaurante){
      long idCozinha  = restaurante.getCozinha().getId();
      Optional<Cozinha> cozinha = cozinhaRepository.findById(idCozinha);
      if(cozinha.isEmpty()){
        throw new EntidadeNaoEncontradaException(String.format("Cozinha de código %d não está cadastrada", idCozinha));
      }    
      restaurante.setCozinha(cozinha.get()); 
      return restauranteRepository.save(restaurante);
    }

    public Restaurante alterarRestaurante(long id, Restaurante restaurante){
      Optional<Restaurante> restauranteAtual = buscarRestaurante(id);
      if(restauranteAtual.isPresent()){
         BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id"); 
         salvarRestaurante(restauranteAtual.get());
         return restauranteAtual.get();
      }
      return null;
    }
}
