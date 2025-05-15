package com.ingsw.conectamente.repository;

import com.ingsw.conectamente.model.entity.Mensaje;

import org.springframework.data.jpa.repository.JpaRepository;


public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
}
