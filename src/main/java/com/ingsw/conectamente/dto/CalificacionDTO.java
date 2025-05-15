package com.ingsw.conectamente.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CalificacionDTO {
    private int puntaje;
    private String comentario;
    private LocalDate fecha;
    private String nombrePaciente;
}

