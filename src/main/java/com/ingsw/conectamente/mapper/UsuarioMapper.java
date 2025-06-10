package com.ingsw.conectamente.mapper;

import com.ingsw.conectamente.dto.UsuarioPerfilDTO;
import com.ingsw.conectamente.dto.UsuarioRegistroDTO;
import com.ingsw.conectamente.model.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UsuarioMapper {
    private final ModelMapper modelMapper;

    public Usuario toUsuarioEntity(UsuarioRegistroDTO registroDTO) {
        return modelMapper.map(registroDTO, Usuario.class);
    }

    public UsuarioPerfilDTO toUsuarioPerfilDTO(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioPerfilDTO.class);
    }
}
