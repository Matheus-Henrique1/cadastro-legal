package com.autoria.cadastroLegal.security.service;

import com.autoria.cadastroLegal.security.dto.UserDTO;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface UserService {

    String register(UserDTO userDTO);

    List<UserDTO> findAll();

    List<UserDTO> searchByName(String name);

    UserDTO searchByCpf(HttpHeaders headers);

    String update(UserDTO userDTO);

    String delete(Integer id);
}
