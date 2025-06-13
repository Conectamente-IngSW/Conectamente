package com.ingsw.conectamente.service;

import com.ingsw.conectamente.dto.CitaDTO;

import java.util.List;

public interface CitaService {
    CitaDTO createCita(CitaDTO citaDTO);
    CitaDTO findCitaById(Integer id);
    List<CitaDTO> findCitaByPsicologoId(Integer idPsicologo);
    List<CitaDTO> findCitaByPacienteId(Integer idPaciente);
    CitaDTO update(Integer id, CitaDTO updateCitaDTO);
    void delete(Integer id);
}
