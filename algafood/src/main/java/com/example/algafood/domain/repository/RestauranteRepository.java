package com.example.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.algafood.domain.model.Restaurante;
@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>{
    
}
