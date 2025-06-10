package com.ingsw.conectamente.mapper;

import com.ingsw.conectamente.dto.VisualizarPacienteDTO;
import com.ingsw.conectamente.dto.VisualizarPsicologoDTO;
import com.ingsw.conectamente.model.entity.Paciente;
import com.ingsw.conectamente.model.entity.Psicologo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VisualizacionPsicologoMapper {
    private final ModelMapper modelMapper;

    public VisualizacionPsicologoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public VisualizarPsicologoDTO toDto(Psicologo psicologo) {
        return modelMapper.map(psicologo, VisualizarPsicologoDTO.class);
    }

    public Psicologo toEntity(VisualizarPsicologoDTO visualizarPsicologoDTO) {
        return modelMapper.map(visualizarPsicologoDTO, Psicologo.class);
    }
}
