package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Restaurante;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Component
public class RestauranteRepository2 {
    @Autowired
    private EntityManager manager;

    public List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal){
      
     var jpql = new StringBuilder(); 

     jpql.append("from Restaurante where 0 = 0 ");
     var parametros = new HashMap<String, Object>();
     
     if(org.springframework.util.StringUtils.hasLength(nome)){
       jpql.append(" and nome like :nome");
       parametros.put("nome", "%"+nome+"%");
     }
     if(taxaInicial!=null){
      jpql.append(" and  taxaFrete >= :taxa_inicial");
      parametros.put("taxa_inicial", taxaInicial);
     }
     if(taxaFinal!=null){
      jpql.append(" and taxaFrete <= :taxa_final");
      parametros.put("taxa_final", taxaFinal);
     }

     TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(),Restaurante.class);

     parametros.forEach((chave,valor)->query.setParameter(chave,valor));

     return query.getResultList();

    }

    public List<Restaurante> find2(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal){
       CriteriaBuilder builder = manager.getCriteriaBuilder();
       CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
       criteria.from(Restaurante.class);
       TypedQuery<Restaurante> query = manager.createQuery(criteria);
       return query.getResultList();
    }
    public List<Restaurante> find3(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
      
      CriteriaBuilder builder = manager.getCriteriaBuilder();
      CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);

      Root<Restaurante> root = criteria.from(Restaurante.class);
      
      var predicates = new ArrayList<>();

      if(org.springframework.util.StringUtils.hasLength(nome)){
      predicates.add(builder.like(root.get("nome"), "%"+nome+"%"));
      }
      if(taxaFreteInicial!=null){
      predicates.add(builder
         .greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
      }
      
        
      if(taxaFreteFinal!=null){
       predicates.add(builder
         .lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
      }

      criteria.where(predicates.toArray(new Predicate[0]));

      TypedQuery<Restaurante> query = manager.createQuery(criteria);

      return query.getResultList();

    }
}
