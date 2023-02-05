package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

       restaurante.setCozinha(cozinha.get());

       return restauranteRepository.save(restaurante);
      
    }

    public Restaurante alterarRestaurante(long id, Restaurante restaurante){
       Optional<Restaurante> restauranteAtual = buscarRestaurante(id);
       if(restauranteAtual.isPresent()){
          long id_cozinha = restaurante.getCozinha().getId();          
            Optional<Cozinha> cozinha = cozinhaRepository.findById(id_cozinha);
            if(cozinha.isEmpty()){
              throw new EntidadeNaoEncontradaException(String.format("Cozinha de código %d não está cadastrada",id));
            }

            BeanUtils.copyProperties(restaurante, restauranteAtual.get(),"id");
            restauranteAtual.get().setCozinha(cozinha.get());           
            return restauranteAtual.get();
          
       }
       return null;
    }

    public void removerRestaurante(long id){
      try{
        restauranteRepository.deleteById(id);
      }catch(EmptyResultDataAccessException e){
        throw new EntidadeNaoEncontradaException(String.format("Restaurante de código %d não está cadastrado",id));
      }
    }

}
