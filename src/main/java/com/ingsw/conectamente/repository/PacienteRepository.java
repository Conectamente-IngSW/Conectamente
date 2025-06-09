package com.ingsw.conectamente.repository;

import com.ingsw.conectamente.model.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacienteRepository  extends JpaRepository<Paciente, Integer> {
    List<Paciente> findByIdPaciente(Integer idPaciente);
}
