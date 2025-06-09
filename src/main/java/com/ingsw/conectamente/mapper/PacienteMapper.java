package com.ingsw.conectamente.mapper;

import com.ingsw.conectamente.dto.PacienteDTO;
import com.ingsw.conectamente.model.entity.Paciente;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {
    private final ModelMapper modelMapper;

    public PacienteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PacienteDTO toDto(Paciente paciente) {
        return modelMapper.map(paciente, PacienteDTO.class);
    }

    public Paciente toEntity(PacienteDTO pacienteDTO) {
        return modelMapper.map(pacienteDTO, Paciente.class);
    }
}
