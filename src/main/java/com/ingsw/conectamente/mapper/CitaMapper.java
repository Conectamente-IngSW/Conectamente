package com.ingsw.conectamente.mapper;

import com.ingsw.conectamente.dto.CitaDTO;
import com.ingsw.conectamente.model.entity.Cita;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CitaMapper {
    private final ModelMapper modelMapper;

    public CitaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CitaDTO toDto(Cita cita) {
        return modelMapper.map(cita, CitaDTO.class);
    }

    public Cita toEntity(CitaDTO citaDTO) {
        return modelMapper.map(citaDTO, Cita.class);
    }
}
