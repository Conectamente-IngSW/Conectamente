package com.ingsw.conectamente.mapper;
import org.modelmapper.ModelMapper;
import com.ingsw.conectamente.dto.VisualizarPacienteDTO;
import com.ingsw.conectamente.model.entity.Paciente;
import org.springframework.stereotype.Component;

@Component
public class VisualizacionPacienteMapper {
    private final ModelMapper modelMapper;

    public VisualizacionPacienteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public VisualizarPacienteDTO toDto(Paciente paciente) {
        return modelMapper.map(paciente, VisualizarPacienteDTO.class);
    }

    public Paciente toEntity(VisualizarPacienteDTO visualizarPacienteDTO) {
        return modelMapper.map(visualizarPacienteDTO, Paciente.class);
    }
}
