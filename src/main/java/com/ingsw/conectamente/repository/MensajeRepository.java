package com.ingsw.conectamente.repository;

import com.ingsw.conectamente.model.entity.Mensaje;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
    List<Mensaje> findByPsicologoIdPsicologo(Integer idPsicologo);
    List<Mensaje> findByPacienteIdPaciente(Integer idPaciente);
}
