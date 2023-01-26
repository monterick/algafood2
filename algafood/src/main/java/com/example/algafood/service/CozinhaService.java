package com.example.algafood.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.algafood.domain.model.Cozinha;
import com.example.algafood.repository.CozinhaRepository;

@Service
public class CozinhaService {
    
    @Autowired
    CozinhaRepository cozinhaRepository;

    public List<Cozinha> listarCozinhas(){
        return cozinhaRepository.findAll();
    }

    public Optional<Cozinha> buscarCozinha(long id){
       Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
       return cozinha;  
    }

    public Cozinha salvarCozinha(Cozinha cozinha){
       return cozinhaRepository.save(cozinha);
    }

    public Cozinha alterarCozinha(long id, Cozinha cozinha){
       Optional<Cozinha> cozinhaAtual = buscarCozinha(id);
       if(cozinhaAtual.isPresent()){
         BeanUtils.copyProperties(cozinha, cozinhaAtual.get(),"id");
         salvarCozinha(cozinhaAtual.get());
         
       }
       return cozinhaAtual.get();
    }

}
