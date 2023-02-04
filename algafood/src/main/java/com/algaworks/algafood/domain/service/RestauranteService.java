package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafood.domain.model.Restaurante;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {
    @Autowired
    RestauranteRepository restauranteRepository;

    public List<Restaurante> listarRestaurantes(){
       return restauranteRepository.findAll();
    }

    public Optional<Restaurante> buscarRestaurante(long id){
       Optional<Restaurante> restaurante = restauranteRepository.findById(id);
       return restaurante;
    }
    public Restaurante salvarRestaurante(Restaurante restaurante){
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
