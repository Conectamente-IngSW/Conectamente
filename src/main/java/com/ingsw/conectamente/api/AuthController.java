package com.ingsw.conectamente.api;

import com.ingsw.conectamente.dto.AuthResponseDTO;
import com.ingsw.conectamente.dto.LoginDTO;
import com.ingsw.conectamente.dto.UsuarioPerfilDTO;
import com.ingsw.conectamente.dto.UsuarioRegistroDTO;
import com.ingsw.conectamente.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;

    //Endpoint para registrar pacientes
    @RequestMapping("/registro/paciente")
    public ResponseEntity <UsuarioPerfilDTO> registroPaciente(@Valid @RequestBody UsuarioRegistroDTO usuarioRegistroDTO) {
        UsuarioPerfilDTO usuarioPerfil = usuarioService.registroPaciente(usuarioRegistroDTO);
        return new ResponseEntity<>(usuarioPerfil, HttpStatus.CREATED);
    }

    //Endpoint para registrar psicologos
    @RequestMapping("/registro/psicologo")
    public ResponseEntity<UsuarioPerfilDTO> registroPsicologo(@Valid @RequestBody UsuarioRegistroDTO usuarioRegistroDTO) {
        UsuarioPerfilDTO usuarioPerfil = usuarioService.registroPsicologo(usuarioRegistroDTO);
        return new ResponseEntity<>(usuarioPerfil, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        AuthResponseDTO authResponse = usuarioService.login(loginDTO);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
