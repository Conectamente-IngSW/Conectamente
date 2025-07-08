package com.ingsw.conectamente.dto;

import com.ingsw.conectamente.enums.Departamento;
import com.ingsw.conectamente.enums.Especialidad;
import com.ingsw.conectamente.enums.Modalidad;
import com.ingsw.conectamente.model.entity.Direccion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VisualizarPsicologoDTO {
    @NotNull
    private Integer id;

    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @NotBlank(message = "El apellido es requerido")
    private String apellido;

    @NotBlank(message = "El numero de colegiatura es requerido")
    @Size(max = 10, message = "El numero de colegiatura debe tener 10 caracteres")
    private String numColegiatura;

    @NotBlank(message = "La disponibilidad es requerida")
    private String disponibilidad;

    @Size(max = 1000, message = "La descripcion no puede exceder los 500 caracteres")
    private String descripcion;

    @NotNull(message = "La tarifa es requerida")
    private Float tarifa;

    @NotNull(message = "La especialidad es requerida")
    private Especialidad especialidad;

    @NotNull(message = "La modalidad es requerida")
    private Modalidad modalidad;

    @NotNull(message = "La dirección es requerida")
    private String direccion;

    @NotNull(message = "El departamento es requerido")
    private Departamento departamento;
}
