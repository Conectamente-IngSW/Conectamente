package com.ingsw.conectamente.repository;

import com.ingsw.conectamente.model.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Integer> {
    List<Cita> findByPsicologoIdPsicologo(Integer idPsicologo);
    List<Cita> findByPacienteIdPaciente(Integer idPaciente);
}
