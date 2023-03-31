package com.autoria.cadastroLegal.usuario.repository;

import com.autoria.cadastroLegal.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findByNomeLike(String nome);

    /**
     * Faz a mesmo coisa que o método findByNome
     */
    @Query(value = "select * from tb_usuario u where u.nome = :nome", nativeQuery = true)
    List<Usuario> buscarPorNome(@Param("nome") String nome);

    Usuario findByCpf(String cpf);

}
