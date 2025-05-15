package com.ingsw.conectamente.dto;


import java.util.List;

public class PerfilPsicologoDTO {
    private String nombreCompleto;
    private String email;
    private String descripcion;
    private String especialidad;
    private double tarifa;
    private int disponibilidad;
    private List<CalificacionDTO> calificaciones;
}

