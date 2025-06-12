package com.ingsw.conectamente.service;

import com.ingsw.conectamente.dto.AuthResponseDTO;
import com.ingsw.conectamente.dto.LoginDTO;
import com.ingsw.conectamente.dto.UsuarioPerfilDTO;
import com.ingsw.conectamente.dto.UsuarioRegistroDTO;

public interface UsuarioService {

    // Register a paciente
    UsuarioPerfilDTO registroPaciente(UsuarioRegistroDTO registroDTO);

    // Register a psicologo
    UsuarioPerfilDTO registroPsicologo(UsuarioRegistroDTO registroDTO);

    //Autenticar un usuario(login)
    AuthResponseDTO login(LoginDTO loginDTO);

    //Actualizar el perfil de un usuario
    UsuarioPerfilDTO updateUsuarioPerfil(Integer idUsuario, UsuarioPerfilDTO usuarioPerfilDTO);

    // Obtener el perfil de un usuario por su ID
    UsuarioPerfilDTO getUsuarioPerfilById(Integer idUsuario);

}
