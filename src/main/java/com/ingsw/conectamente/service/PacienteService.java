package com.ingsw.conectamente.service;
import com.ingsw.conectamente.dto.PacienteDTO;
import com.ingsw.conectamente.dto.PsicologoDTO;
import com.ingsw.conectamente.dto.VisualizarPacienteDTO;
import com.ingsw.conectamente.enums.Especialidad;
import com.ingsw.conectamente.model.entity.Paciente;
import com.ingsw.conectamente.model.entity.Psicologo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PacienteService {
    List<VisualizarPacienteDTO> getAll();
    PacienteDTO create(PacienteDTO pacienteDTO);
    VisualizarPacienteDTO findById(Integer id);
    PacienteDTO update(Integer id, PacienteDTO updatePacienteDTO);
    void delete(Integer id);
}
