package com.ingsw.conectamente.repository;

import com.ingsw.conectamente.model.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository  extends JpaRepository<Paciente, Integer> {

    boolean existsByNombrePacienteAndApellidoPaciente(String nombrePaciente, String apellidoPaciente);

    boolean existsByNombrePacienteAndApellidoPacienteAndUsuario_idUsuarioNot(String nombrePaciente, String apellidoPaciente, Integer idUsuario);


}
