package com.ingsw.conectamente.repository;

import com.ingsw.conectamente.enums.Especialidad;
import com.ingsw.conectamente.model.entity.Psicologo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PsicologoRepository  extends JpaRepository<Psicologo, Integer> {
    List<Psicologo> findByEspecialidad(Especialidad especialidad);
    List<Psicologo> findByDisponibilidadContaining(String disponibilidad);
    List<Psicologo> findByTarifaBetween(Float min, Float max);
    List<Psicologo> findByNumColegiatura(String numColegiatura);
    List<Psicologo> findByEmail(String email);
    boolean existsByNumColegiatura(String numColegiatura);

    boolean existsByNombreAndApellido(String nombre, String apellido);

    // Método para verificar si existe un psicólogo con el mismo nombre y apellido, excepto el usuario actual
    boolean existsByNombreAndApellidoAndUsuario_idUsuarioNot(String nombre, String apellido, Integer idUsuario);

}


