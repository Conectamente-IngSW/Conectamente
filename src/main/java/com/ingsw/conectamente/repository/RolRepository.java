package com.ingsw.conectamente.repository;

import com.ingsw.conectamente.model.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    //Buscar un rol por su nombre(usando el enum)
    Optional<Rol> findByName(String name);
}
