package com.autoria.cadastroLegal.mapper;

import com.autoria.cadastroLegal.dto.UserDTO;
import com.autoria.cadastroLegal.entity.User;
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
public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO toDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO toDTOSemId(User usuario) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.typeMap(User.class, UserDTO.class)
                .addMappings(mapping -> mapping.skip(UserDTO::setId));
        return modelMapper.map(usuario, UserDTO.class);
    }

    public User toEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public List<UserDTO> entityToDTOListNoId(List<User> users) {
        return users.stream()
                .map(this::toDTOSemId)
                .collect(Collectors.toList());
    }

    public List<UserDTO> entityToDTOList(List<User> users) {
        return users.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


}
