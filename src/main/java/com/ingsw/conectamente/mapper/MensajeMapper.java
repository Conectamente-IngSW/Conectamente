package com.ingsw.conectamente.mapper;

import com.ingsw.conectamente.dto.MensajeDTO;
import com.ingsw.conectamente.model.entity.Mensaje;
import com.ingsw.conectamente.model.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MensajeMapper {
    private final ModelMapper modelMapper;

    public MensajeDTO toDto(Mensaje mensaje) {
        MensajeDTO dto = modelMapper.map(mensaje, MensajeDTO.class);
        if (mensaje.getUsuario() != null) {
            dto.setUsuarioId(mensaje.getUsuario().getIdUsuario());
        }
        return dto;
    }

    public Mensaje toEntity(MensajeDTO dto) {
        Mensaje mensaje = modelMapper.map(dto, Mensaje.class);
        if (dto.getUsuarioId() != null) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(dto.getUsuarioId());
            mensaje.setUsuario(usuario);
        }
        return mensaje;
    }
}