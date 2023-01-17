package com.example.algafood;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algafood.domain.exceptions.EntidadeEmUsoException;
import com.example.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.example.algafood.domain.model.Cozinha;
import com.example.algafood.domain.services.CozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    CozinhaService cozinhaService;

    @GetMapping
    public List<Cozinha> listarCozinhas() {
        return cozinhaService.listarCozinhas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscarCozinha(@PathVariable(value = "id") long id) {
        Optional<Cozinha> cozinha = cozinhaService.buscarCozinha(id);
        if (cozinha.isPresent()) {
            return ResponseEntity.ok(cozinha.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha salvarCozinha(@RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtual = cozinhaService.salvarCozinha(cozinha);
        return cozinhaAtual;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerCozinha(@PathVariable(value = "id") long id) {
        try {
            cozinhaService.removerCozinha(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
