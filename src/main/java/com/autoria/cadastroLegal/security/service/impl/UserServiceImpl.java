package com.autoria.cadastroLegal.security.service.impl;

import com.autoria.cadastroLegal.security.dto.UserDTO;
import com.autoria.cadastroLegal.security.entity.User;
import com.autoria.cadastroLegal.security.repository.UserRepository;
import com.autoria.cadastroLegal.security.service.UserService;
import com.autoria.cadastroLegal.utils.Messages;
import com.autoria.cadastroLegal.utils.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
            if (Objects.nonNull(userRepository.findByCpf(userDTO.getCpf().replaceAll("[^0-9]", "")))) {
                return Messages.USER_ALREADY_REGISTERED;
            } else if (Objects.nonNull(userRepository.findByLogin(userDTO.getLogin()))) {
                return Messages.LOGIN_ALREADY_REGISTERED;
            } else {
                userDTO.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
                userDTO.setStatus(true);
                userDTO.setCpf(userDTO.getCpf().replaceAll("[^0-9]", ""));
                User user = userMapper.toEntity(userDTO);
                userRepository.save(user);
                return Messages.REGISTERED_SUCCESSFULLY;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Falta implementar as validações
     *
     * @return
     */
    @Override
    public List<UserDTO> findAll() {
        List<User> UsersList = userRepository.findAll();
        return userMapper.entityToDTOList(UsersList);
    }

    /**
     * Falta implementar as validações
     *
     * @param name
     * @return
     */
    @Override
    public List<UserDTO> searchByName(String name) {
        List<User> UsersList = userRepository.findByNameLike(name);
        return userMapper.entityToDTOListNoId(UsersList);
    }

    /**
     * Falta implementar as validações
     *
     * @param headers
     * @return
     */
    @Override
    public UserDTO searchByCpf(HttpHeaders headers) {
        if (headers.containsKey("cpf")) {
            UserDTO user = userMapper.toDTO(userRepository.findByCpf(headers.get("cpf").get(0)));
            if (Objects.nonNull(user)) {
                return user;
            }
        } else {
            return null;
        }
        return null;
    }

    /**
     * Falta implementar as validações
     *
     * @param userDTO
     * @return
     */
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
