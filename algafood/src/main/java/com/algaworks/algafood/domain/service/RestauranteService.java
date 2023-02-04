package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.exceptions.EntidadeNaoEncontradaException;

@Service
public class RestauranteService {
    @Autowired
    RestauranteRepository restauranteRepository;

    @Autowired
    CozinhaRepository cozinhaRepository;

    public List<Restaurante> listarRestaurantes(){
       return restauranteRepository.findAll();
    }

    public Optional<Restaurante> buscarRestaurante(long id){
       Optional<Restaurante> restaurante = restauranteRepository.findById(id);
       return restaurante;
    }
    public Restaurante salvarRestaurante(Restaurante restaurante){
       long id_cozinha = restaurante.getCozinha().getId();
       Optional<Cozinha> cozinha = cozinhaRepository.findById(id_cozinha);    
       if(cozinha.isEmpty()){
         throw new EntidadeNaoEncontradaException(String.format("Cozinha de código %d não está cadastrada", id_cozinha));
       }

       return restauranteRepository.save(restaurante);
      
    }

    public Restaurante alterarRestaurante(long id, Restaurante restaurante){
       Optional<Restaurante> restaurante2 = buscarRestaurante(id);
       if(restaurante2.isPresent()){
         BeanUtils.copyProperties(restaurante, restaurante2.get(),"id");
         salvarRestaurante(restaurante2.get());
         return restaurante2.get();
       }
       return null;
    }

}
