package com.ingsw.conectamente.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioRegistroDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Email(message = "El email debe ser válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    private String telefono;
    private String fechaNacimiento;
    private String ciudad;
    private String pais;
    private String codigoPostal;
    private String descripcion;
    private String fotoPerfilUrl;
    private String rol; // Puede ser "PACIENTE", "PSICOLOGO" o "ADMINISTRADOR"
}
