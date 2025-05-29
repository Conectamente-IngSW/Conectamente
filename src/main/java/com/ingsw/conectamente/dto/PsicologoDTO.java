package com.ingsw.conectamente.dto;

import com.ingsw.conectamente.enums.Especialidad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PsicologoDTO {
    private Integer idPsicologo;

    @NotBlank(message = "El numero de colegiatura es requerido")
    @Size(max = 10, message = "El numero de colegiatura debe tener 10 caracteres")
    private String numColegiatura;

    @NotBlank(message = "La disponibilidad es requerida")
    private String disponibilidad;

    @Size(max = 500, message = "La descripcion no puede exceder los 500 caracteres")
    private String descripcion;

    @NotBlank(message = "La tarifa es requerida")
    private Float tarifa;

    @NotBlank(message = "La especialidad es requerida")
    private Especialidad especialidad;
}
