package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Restaurante;

import jakarta.persistence.EntityManager;
@Component
public class RestauranteRepository2 {
    @Autowired
    EntityManager manager;

    public List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal){
        var jpql = "from Restaurante where nome like :nome and taxa_frete between :taxa_inicial and :taxa_final";
        return manager.createQuery(jpql,Restaurante.class)
        .setParameter(":nome", "%"+nome+"%")
        .setParameter(":taxa_inicial",taxaInicial)
        .setParameter(":taxa_final", taxaFinal)
        .getResultList();
    }
}
