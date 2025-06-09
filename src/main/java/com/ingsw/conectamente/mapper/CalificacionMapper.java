package com.ingsw.conectamente.mapper;

import com.ingsw.conectamente.dto.CalificacionDTO;
import com.ingsw.conectamente.model.entity.Calificacion;
import org.modelmapper.ModelMapper;
import com.ingsw.conectamente.dto.PsicologoDTO;
import com.ingsw.conectamente.model.entity.Psicologo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CalificacionMapper {

    private final ModelMapper modelMapper;

    public CalificacionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CalificacionDTO toDto(Calificacion calificacion) {
        return modelMapper.map(calificacion, CalificacionDTO.class);
    }

    public Calificacion toEntity(CalificacionDTO calificacionDTO) {
        return modelMapper.map(calificacionDTO, Calificacion.class);}
}
