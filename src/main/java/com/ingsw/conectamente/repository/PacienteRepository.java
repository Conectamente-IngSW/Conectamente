package com.ingsw.conectamente.repository;

import com.ingsw.conectamente.dto.VisualizarPacienteDTO;
import com.ingsw.conectamente.model.entity.Paciente;
import com.ingsw.conectamente.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository  extends JpaRepository<Paciente, Integer> {

    boolean existsByNombreAndApellido(String nombre, String apellido);

    boolean existsByNombreAndApellidoAndUsuario_idUsuarioNot(String nombre, String apellido, Integer idUsuario);


    List<Paciente> findByIdPaciente(Integer idPaciente);
    List<Paciente> findByDni(String dni);

    Optional<Paciente> findPacienteByUsuario_IdUsuario(Integer idUsuario);

}
