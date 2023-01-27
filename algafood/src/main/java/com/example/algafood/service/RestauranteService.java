package com.example.algafood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.algafood.domain.model.Restaurante;
import com.example.algafood.repository.RestauranteRepository;

@Service
public class RestauranteService {
    @Autowired
    RestauranteRepository restauranteRepository;

    public List<Restaurante> listarRestaurantes(){
        return  restauranteRepository.findAll();
    }
}
