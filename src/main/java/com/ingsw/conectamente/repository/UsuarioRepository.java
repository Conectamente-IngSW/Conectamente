package com.ingsw.conectamente.repository;

import com.ingsw.conectamente.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsByEmail(String email);

    //Metodo para buscar un usuario por su email (será utilizado en el login)
    Optional<Usuario> findByEmail(String email);

}
