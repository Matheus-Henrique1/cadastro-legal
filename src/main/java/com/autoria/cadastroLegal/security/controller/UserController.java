package com.autoria.cadastroLegal.security.controller;

import com.autoria.cadastroLegal.security.dto.UserDTO;
import com.autoria.cadastroLegal.security.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> register(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.register(userDTO));
    }

    @GetMapping("/find-all")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserDTO> findAll() {
        return ResponseEntity.ok().body(userService.findAll()).getBody();
    }

    @GetMapping("/search-by-name/{name}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserDTO> searchByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(userService.searchByName(name)).getBody();
    }

    @GetMapping("/search-by-cpf")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<UserDTO> buscarPorCpf(@RequestHeader HttpHeaders headers) {
        return ResponseEntity.ok(userService.searchByCpf(headers));
    }

    @PutMapping("update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> update(@RequestBody UserDTO userDTO, @PathVariable("id") Integer id) {
        return ResponseEntity.ok(userService.update(userDTO));
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(userService.delete(id));
    }


}
