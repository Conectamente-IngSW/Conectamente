package com.ingsw.conectamente.service;

import com.ingsw.conectamente.dto.CalificacionDTO;

import java.util.List;

public interface CalificacionService {
    CalificacionDTO createCalificacion(CalificacionDTO calificacionDTO);
    CalificacionDTO findCalificacionById(Integer id);
    List<CalificacionDTO> findCalificacionesByPsicologoId(Integer idPsicologo);
    CalificacionDTO update(Integer id, CalificacionDTO updateCalificacionDTO);
    void delete(Integer id);
}
