package com.ingsw.conectamente.service;

import com.ingsw.conectamente.model.entity.Calificacion;
import com.ingsw.conectamente.model.entity.Psicologo;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CalificacionService {
    Calificacion createCalificacion(Calificacion calificacion);
    Calificacion findCalificacionById(Integer id);
    List<Calificacion> findCalificacionesByPsicologoId(Integer idPsicologo);

}
