package com.autoria.cadastroLegal.repository;

import com.autoria.cadastroLegal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByNameLike(String name);

    /**
     * Faz a mesmo coisa que o método findByNome
     */
    @Query(value = "select * from tb_user u where u.name = :name", nativeQuery = true)
    List<User> searchByName(@Param("name") String name);

    User findByCpf(String cpf);

}
