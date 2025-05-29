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

}


