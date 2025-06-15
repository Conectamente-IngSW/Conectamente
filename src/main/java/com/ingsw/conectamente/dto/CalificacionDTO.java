package com.ingsw.conectamente.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CalificacionDTO {

    private Integer idCalificacion;

    @NotNull(message = "El puntaje no puede ser nulo")
    @Min(value = 1, message = "El puntaje mínimo es 1")
    @Max(value = 5, message = "El puntaje máximo es 5")
    private Integer puntaje;

    @Size(max = 500, message = "La descripcion no puede exceder los 500 caracteres")
    private String comentario;
    private LocalDateTime fecha;

    @NotNull(message = "El ID del paciente no puede ser nulo")
    private Integer pacienteId;

    @NotNull(message = "El ID del psicólogo no puede ser nulo")
    private Integer psicologoId;

}
