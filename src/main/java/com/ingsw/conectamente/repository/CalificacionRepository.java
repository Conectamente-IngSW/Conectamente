package com.ingsw.conectamente.repository;

import com.ingsw.conectamente.model.entity.Calificacion;
import com.ingsw.conectamente.model.entity.Psicologo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalificacionRepository extends JpaRepository<Calificacion, Integer> {
    List<Calificacion> findByPsicologoIdPsicologo(Integer idPsicologo);
    List<Calificacion> findByIdCalificacion(Integer idCalificacion);

}
