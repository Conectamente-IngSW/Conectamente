package com.ingsw.conectamente.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CitaDTO {
    private Integer idCita;

    @NotNull(message = "La fecha de la cita es requerida")
    private LocalDate fechaCita;

    @NotNull(message = "La hora de la cita es requerida")
    private LocalTime horaCita;

    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    private String descripcion;

    @NotNull(message = "El ID del paciente es obligatorio")
    private Integer idPaciente;

    @NotNull(message = "El ID del psicólogo es obligatorio")
    private Integer idPsicologo;
}
