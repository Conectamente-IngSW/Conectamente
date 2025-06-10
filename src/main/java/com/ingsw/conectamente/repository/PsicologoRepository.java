package com.ingsw.conectamente.repository;

import com.ingsw.conectamente.enums.Especialidad;
import com.ingsw.conectamente.model.entity.Psicologo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PsicologoRepository  extends JpaRepository<Psicologo, Integer> {
    List<Psicologo> findByEspecialidad(Especialidad especialidad);
    List<Psicologo> findByDisponibilidadContaining(String disponibilidad);
    List<Psicologo> findByTarifaBetween(Float min, Float max);
    List<Psicologo> findByNumColegiatura(String numColegiatura);

    boolean existsByNombrePsicologoAndApellidoPsicologo(String nombrePsicologo, String apellidoPsicologo);

    // Método para verificar si existe un psicólogo con el mismo nombre y apellido, excepto el usuario actual
    boolean existsByNombrePsicologoAndApellidoPsicologoAndUsuario_idUsuarioNot(String nombrePsicologo, String apellidoPsicologo, Integer idUsuario);

}


