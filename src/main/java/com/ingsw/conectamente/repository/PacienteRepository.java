package com.ingsw.conectamente.repository;

import com.ingsw.conectamente.model.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacienteRepository  extends JpaRepository<Paciente, Integer> {

    boolean existsByNombreAndApellido(String nombre, String apellido);

    boolean existsByNombreAndApellidoAndUsuario_idUsuarioNot(String nombre, String apellido, Integer idUsuario);


    List<Paciente> findByIdPaciente(Integer idPaciente);
    List<Paciente> findByDni(String dni);
}
