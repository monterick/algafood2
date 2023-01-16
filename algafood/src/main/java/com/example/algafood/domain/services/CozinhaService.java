package com.example.algafood.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.algafood.domain.model.Cozinha;
import com.example.algafood.domain.repository.CozinhaRepository;

@Service
public class CozinhaService {

    @Autowired
    CozinhaRepository cozinhaRepository;

    public List<Cozinha> listarCozinhas() {
        return cozinhaRepository.findAll();
    }

    public Optional<Cozinha> buscarCozinha(long id) {
        Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
        return cozinha;
    }

}
