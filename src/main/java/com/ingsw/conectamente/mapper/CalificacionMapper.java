package com.ingsw.conectamente.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import com.ingsw.conectamente.dto.CalificacionDTO;
import com.ingsw.conectamente.model.entity.Calificacion;

@Component
@RequiredArgsConstructor
public class CalificacionMapper {

    private final ModelMapper modelMapper;

    public CalificacionDTO toDto(Calificacion calificacion) {
        return modelMapper.map(calificacion, CalificacionDTO.class);
    }

    public Calificacion toEntity(CalificacionDTO dto) {
        return modelMapper.map(dto, Calificacion.class);
    }
}