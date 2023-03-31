package com.autoria.cadastroLegal.usuario.service;

import com.autoria.cadastroLegal.usuario.dto.UsuarioDTO;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface UsuarioService {

    String cadastrar(UsuarioDTO usuarioDTO);

    List<UsuarioDTO> buscarTodos();

    List<UsuarioDTO> buscarPorNome(String nome);

    UsuarioDTO buscarPorCpf(HttpHeaders headers);

    String atualizar(UsuarioDTO usuarioDTO);
}
