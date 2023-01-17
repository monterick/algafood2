package com.algaworks.algafood;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.services.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    
    @Autowired
    RestauranteService restauranteService;

    @GetMapping
    public List<Restaurante> listarRestaurantes(){
        return restauranteService.listarRestaurantes();
    }

}