package com.example.algafood.domain2.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.example.algafood.domain.model.Restaurante;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;



@Repository
public class RestauranteRepositoryImpl {

  @PersistenceContext
  EntityManager entityManager;

  public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal ){
     var jpql = new StringBuilder();
     jpql.append("from Restaurante where 0 = 0 ");

     var parametros = new HashMap<String, Object>();

     if(StringUtils.hasLength(nome)){
       jpql.append("and nome like :nome ");
       parametros.put("%"+nome+"%",nome);
     }
     if(taxaFreteInicial!=null){
       jpql.append("and taxaFrete >= :taxaFreteInicial ");
       parametros.put("taxaFreteInicial", taxaFreteInicial);
     }
     if(taxaFreteFinal!=null){
      jpql.append("and taxaFrete <= :taxaFreteFinal");
      parametros.put("taxaFreteFinal", taxaFreteFinal);
     }

     TypedQuery<Restaurante> query = entityManager.createNamedQuery(jpql.toString(),Restaurante.class);

     parametros.forEach((chave, valor)->{
         query.setParameter(chave, valor);         
     });

     return query.getResultList();
     
  }

}

