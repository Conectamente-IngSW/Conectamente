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
        PsicologoDTO dto = modelMapper.map(psicologo, PsicologoDTO.class);
        if (psicologo.getUsuario_idUsuario() != null) {
            dto.setNombre(psicologo.getUsuario_idUsuario().getNombre());
            dto.setApellido(psicologo.getUsuario_idUsuario().getApellido());
        }
        return dto;
    }

    public Psicologo toEntity(PsicologoDTO psicologoDTO) {
        return modelMapper.map(psicologoDTO, Psicologo.class);
    }
}
