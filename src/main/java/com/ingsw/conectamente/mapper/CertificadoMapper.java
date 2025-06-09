package com.ingsw.conectamente.mapper;

import com.ingsw.conectamente.dto.CertificadoDTO;
import com.ingsw.conectamente.model.entity.Certificado;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Component;

@Component
public class CertificadoMapper {

    private final ModelMapper modelMapper;

    public CertificadoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CertificadoDTO toDto(Certificado certificado) {
        return modelMapper.map(certificado, CertificadoDTO.class);
    }

    public Certificado toEntity(CertificadoDTO certificadoDTO) {
        return modelMapper.map(certificadoDTO, Certificado.class);
    }
}
