package com.ingsw.conectamente.mapper;
import org.modelmapper.ModelMapper;
import com.ingsw.conectamente.dto.PsicologoDTO;
import com.ingsw.conectamente.model.entity.Psicologo;
import org.springframework.stereotype.Component;

@Component
public class PsicologoMapper {

    private final ModelMapper modelMapper;

    public PsicologoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PsicologoDTO toDto(Psicologo psicologo) {
        return modelMapper.map(psicologo, PsicologoDTO.class);
    }

    public Psicologo toEntity(PsicologoDTO psicologoDTO) {
        return modelMapper.map(psicologoDTO, Psicologo.class);
    }
}
