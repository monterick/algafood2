package com.example.algafood.domain2.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.algafood.domain.model.Restaurante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;



@Repository
public class RestauranteRepositoryImpl {

  @PersistenceContext
  EntityManager entityManager;

  public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal ){
    var jpql = "from Restaurante where nome like :nome"
      +" and taxaFrete between :taxaInicial and :taxaFinal";
      return entityManager.createQuery(jpql,Restaurante.class)
      .setParameter("nome", "%"+nome+"%")
      .setParameter("taxaInicial", taxaFreteInicial )
      .setParameter("taxaFinal", taxaFreteFinal )
      .getResultList()
      ;
  }

}

