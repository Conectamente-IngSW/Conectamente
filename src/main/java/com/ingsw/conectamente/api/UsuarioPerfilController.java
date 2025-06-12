package com.ingsw.conectamente.api;

import com.ingsw.conectamente.dto.UsuarioPerfilDTO;
import com.ingsw.conectamente.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario/perfil")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('PSICOLOGO', 'PACIENTE')") // Restriction at class level
public class UsuarioPerfilController {

    private final UsuarioService usuarioService;

    //Actualizar el perfil del usuario
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioPerfilDTO> updatePerfil(@PathVariable Integer id, @Valid @RequestBody UsuarioPerfilDTO usuarioPerfilDTO) {
        UsuarioPerfilDTO updatedPerfil = usuarioService.updateUsuarioPerfil(id, usuarioPerfilDTO);
        return new ResponseEntity<>(updatedPerfil, HttpStatus.OK);
    }

    //Obtener el perfil del usuario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioPerfilDTO> getPerfilById(@PathVariable Integer id) {
        UsuarioPerfilDTO usuarioPerfil = usuarioService.getUsuarioPerfilById(id);
        if (usuarioPerfil == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(usuarioPerfil, HttpStatus.OK);
    }
}
