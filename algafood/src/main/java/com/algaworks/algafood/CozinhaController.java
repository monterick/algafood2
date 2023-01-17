package com.algaworks.algafood;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
    @GetMapping
    public String listarCozinhas(){
        return "teste cozinhas";
    }
}
