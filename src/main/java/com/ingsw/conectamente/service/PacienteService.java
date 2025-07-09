package com.ingsw.conectamente.service;
import com.ingsw.conectamente.dto.PacienteDTO;
import com.ingsw.conectamente.dto.VisualizarPacienteDTO;

import java.util.List;

public interface PacienteService {
    List<VisualizarPacienteDTO> getAll();
    PacienteDTO create(PacienteDTO pacienteDTO);
    VisualizarPacienteDTO findById(Integer id);
    PacienteDTO update(Integer id, PacienteDTO updatePacienteDTO);
    void delete(Integer id);
    //buscar por paciente por id de usuario
    VisualizarPacienteDTO findByUsuarioId(Integer id);
}
