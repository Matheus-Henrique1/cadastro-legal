package com.autoria.cadastroLegal.service.impl;

import com.autoria.cadastroLegal.dto.UserDTO;
import com.autoria.cadastroLegal.entity.User;
import com.autoria.cadastroLegal.mapper.UserMapper;
import com.autoria.cadastroLegal.repository.UserRepository;
import com.autoria.cadastroLegal.service.UserService;
import com.autoria.cadastroLegal.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public String register(UserDTO userDTO) {
        try {
            if (Objects.nonNull(userRepository.findByCpf(userDTO.getCpf()))) {
                return Messages.USER_ALREADY_REGISTERED;
            }
            userDTO.setStatus(true);
            userDTO.setCpf(userDTO.getCpf().replaceAll("[^0-9]", ""));
            User user = userMapper.toEntity(userDTO);
            userRepository.save(user);
            return Messages.REGISTERED_SUCCESSFULLY;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> UsersList = userRepository.findAll();
        return userMapper.entityToDTOList(UsersList);
    }

    @Override
    public List<UserDTO> searchByName(String name) {
        List<User> UsersList = userRepository.findByNameLike(name);
        return userMapper.entityToDTOListNoId(UsersList);
    }

    @Override
    public UserDTO searchByCpf(HttpHeaders headers) {
        if (headers.containsKey("cpf")) {
            return userMapper.toDTO(userRepository.findByCpf(headers.get("cpf").get(0)));
        } else {
            return null;
        }
    }

    @Override
    public String update(UserDTO userDTO) {
        User usuario = userMapper.toEntity(userDTO);
        userRepository.save(usuario);
        return "Cadastro atualizado com sucesso!";
    }

    @Override
    public String delete(Integer id) {
        Optional<User> usuario = userRepository.findById(id);
        if (!usuario.isPresent()) {
            return Messages.USER_NOT_FOUND;
        } else {
            userRepository.deleteById(id);
            return Messages.DELETED_USER;
        }
    }

}
