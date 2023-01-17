package com.algaworks.algafood.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

    @Autowired
    RestauranteRepository restauranteRepository;

    public List<Restaurante> listarRestaurantes(){
        return restauranteRepository.findAll();
    }

}
