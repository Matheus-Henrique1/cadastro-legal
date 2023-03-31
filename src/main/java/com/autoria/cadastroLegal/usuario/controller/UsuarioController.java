package com.autoria.cadastroLegal.usuario.controller;

import com.autoria.cadastroLegal.usuario.dto.UsuarioDTO;
import com.autoria.cadastroLegal.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> cadastrar(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.cadastrar(usuarioDTO));
    }

    @GetMapping("buscar-todos")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UsuarioDTO> buscarTodos() {
        return ResponseEntity.ok().body(usuarioService.buscarTodos()).getBody();
    }

    @GetMapping("buscar-por-nome/{nome}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UsuarioDTO> buscarPorNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(usuarioService.buscarPorNome(nome)).getBody();
    }

    @GetMapping("buscar-por-cpf")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<UsuarioDTO> buscarPorCpf(@RequestHeader HttpHeaders headers) {
        return ResponseEntity.ok(usuarioService.buscarPorCpf(headers));
    }

    @PutMapping("atualizar")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> atualizar(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.atualizar(usuarioDTO));
    }

    @DeleteMapping("deletar")
    @ResponseStatus(HttpStatus.OK)
    public String deletar() {
        return null;
    }


}
