package com.autoria.cadastroLegal.usuario.service;

import com.autoria.cadastroLegal.usuario.dto.UsuarioDTO;
import com.autoria.cadastroLegal.usuario.dto.UsuarioMapper;
import com.autoria.cadastroLegal.usuario.entity.Usuario;
import com.autoria.cadastroLegal.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public String cadastrar(UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
            usuarioRepository.save(usuario);
            return "Cadastrado com sucesso!";
        } catch (Exception e) {
            return "Não foi possível cadastrar";
        }
    }

    @Override
    public List<UsuarioDTO> buscarTodos() {
        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        return usuarioMapper.entidadeParaListaDeDTOSemId(listaUsuarios);
    }

    @Override
    public List<UsuarioDTO> buscarPorNome(String nome) {
        List<Usuario> listaUsuarios = usuarioRepository.findByNomeLike(nome);
        return usuarioMapper.entidadeParaListaDeDTOSemId(listaUsuarios);
    }

    @Override
    public UsuarioDTO buscarPorCpf(HttpHeaders headers) {
        if (headers.containsKey("cpf")) {
            return usuarioMapper.toDTOSemId(usuarioRepository.findByCpf(headers.get("cpf").get(0)));
        } else {
            return null;
        }
    }

    @Override
    public String atualizar(UsuarioDTO usuarioDTO){
        return null;
    }

}
