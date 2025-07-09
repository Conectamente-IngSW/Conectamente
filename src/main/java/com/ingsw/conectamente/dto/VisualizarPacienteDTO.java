package com.ingsw.conectamente.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VisualizarPacienteDTO {
    private Integer idPaciente;

    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @NotBlank(message = "El apellido es requerido")
    private String apellido;

    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    private String descripcion;

}
