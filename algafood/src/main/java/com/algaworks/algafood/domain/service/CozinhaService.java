package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.exceptions.EntidadeEmUsoException;

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
         BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
         salvarCozinha(cozinhaAtual.get());
         return cozinhaAtual.get();
      }
      return null;
    }
    public void removerCozinha(long id){
     try{   
      cozinhaRepository.deleteById(id);
     }catch(EmptyResultDataAccessException e){
       throw new EntidadeNaoEncontradaException(String.format("Cozinha de código %d não está cadastrada no sistema", id));
     }catch(DataIntegrityViolationException e){
       throw new EntidadeEmUsoException(String.format("Cozinha de código %d está em uso", id));
     }
    }
}
