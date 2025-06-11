package com.ingsw.conectamente.mapper;

import com.ingsw.conectamente.dto.AuthResponseDTO;
import com.ingsw.conectamente.dto.LoginDTO;
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
        UsuarioPerfilDTO usuarioPerfilDTO = modelMapper.map(usuario, UsuarioPerfilDTO.class);

        if(usuario.getPaciente()!=null){
            usuarioPerfilDTO.setNombre(usuario.getPaciente().getNombre());
            usuarioPerfilDTO.setApellido(usuario.getPaciente().getApellido());
            usuarioPerfilDTO.setDni(usuario.getPaciente().getDni());
        }

        if(usuario.getPsicologo()!=null){
            usuarioPerfilDTO.setNombre(usuario.getPsicologo().getNombre());
            usuarioPerfilDTO.setApellido(usuario.getPsicologo().getApellido());
            usuarioPerfilDTO.setNumColegiatura(usuario.getPsicologo().getNumColegiatura());
        }

        return usuarioPerfilDTO;
    }

    //Convertir de LoginDTO a Usuario (cuando procesas el login
    public Usuario toUsuarioEntity(LoginDTO loginDTO) {
        return modelMapper.map(loginDTO, Usuario.class);
    }

    //Convertir de User a AuthResponseDTO para la respuesta de autenticación
    public AuthResponseDTO toAuthResponseDTO(Usuario usuario, String token) {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();

        //Obtener el nombre y apellido
        String nombre = (usuario.getPaciente() != null) ? usuario.getPaciente().getNombre()
                : (usuario.getPsicologo() != null) ? usuario.getPsicologo().getNombre()
                : "Admin";
        String apellido = (usuario.getPaciente() != null) ? usuario.getPaciente().getApellido()
                : (usuario.getPsicologo() != null) ? usuario.getPsicologo().getApellido()
                : "User";

        authResponseDTO.setNombre(nombre);
        authResponseDTO.setApellido(apellido);

        authResponseDTO.setRol(usuario.getRol().getName().name());

        return authResponseDTO;
    }
}
