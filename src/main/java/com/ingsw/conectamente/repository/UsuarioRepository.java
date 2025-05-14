package com.ingsw.conectamente.repository;

import com.ingsw.conectamente.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
