package com.ingsw.conectamente.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String token;         // El token JWT generado
    private String nombre;        // El primer nombre del usuario
    private String apellido;      // El apellido del usuario
    private String rol;           // El rol del usuario (ej. "ROL_PACIENTE", "ROL_PSICOLOGO", etc.)
}
