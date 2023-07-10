package com.autoria.cadastroLegal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/video")
public class Teste {

    @GetMapping("/free")
    public String retornoFree(){
        return "Este é um endpoint liberado pela nossa API";
    }

    @GetMapping("/auth")
    public String retornoAuth(){
        return "Este é um endpoint que precis ade autenticação";
    }
}
