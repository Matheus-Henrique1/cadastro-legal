package com.autoria.cadastroLegal.usuario.dto;

import com.autoria.cadastroLegal.usuario.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioDTO toDTO(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public UsuarioDTO toDTOSemId(Usuario usuario) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.typeMap(Usuario.class, UsuarioDTO.class)
                .addMappings(mapping -> mapping.skip(UsuarioDTO::setId));
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public Usuario toEntity(UsuarioDTO usuarioDTO) {
        return modelMapper.map(usuarioDTO, Usuario.class);
    }

    public List<UsuarioDTO> entidadeParaListaDeDTOSemId(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(this::toDTOSemId)
                .collect(Collectors.toList());
    }

    public List<UsuarioDTO> entidadeParaListaDeDTO(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


}
