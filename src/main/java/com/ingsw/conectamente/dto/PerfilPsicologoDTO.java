package com.ingsw.conectamente.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class PerfilPsicologoDTO {
    private String nombre;
    private String apellido;
    private String email;
    private String descripcion;
    private String especialidad;
    private BigDecimal tarifa;
    private int disponibilidad;
    private List<CalificacionDTO> calificaciones;
    private String direccion;
}

