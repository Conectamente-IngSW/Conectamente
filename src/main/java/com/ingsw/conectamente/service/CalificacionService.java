package com.ingsw.conectamente.service;

import com.ingsw.conectamente.dto.CalificacionDTO;
import com.ingsw.conectamente.dto.PsicologoDTO;
import com.ingsw.conectamente.model.entity.Calificacion;
import com.ingsw.conectamente.model.entity.Paciente;
import com.ingsw.conectamente.model.entity.Psicologo;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CalificacionService {
    CalificacionDTO createCalificacion(CalificacionDTO calificacionDTO);
    CalificacionDTO findCalificacionById(Integer id);
    List<CalificacionDTO> findCalificacionesByPsicologoId(Integer idPsicologo);
    CalificacionDTO update(Integer id, CalificacionDTO updateCalificacionDTO);
    void delete(Integer id);
}
