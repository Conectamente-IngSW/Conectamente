package com.ingsw.conectamente.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PacienteDTO {
    private Integer idPaciente;

    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @NotBlank(message = "El apellido es requerido")
    private String apellido;

    @NotBlank(message = "El dni es requerido")
    private String dni;

    @NotNull(message = "La edad es requerida")
    private Integer edad;

    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    private String descripcion;

}

