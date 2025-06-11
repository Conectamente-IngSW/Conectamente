package com.ingsw.conectamente.dto;

import com.ingsw.conectamente.enums.Especialidad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PsicologoDTO {
    private Integer idPsicologo;

    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @NotBlank(message = "El apellido es requerido")
    private String apellido;

    @NotBlank(message = "El dni es requerido")
    private String dni;

    @NotNull(message = "La edad es requerida")
    private Integer edad;

    @NotBlank(message = "El numero de colegiatura es requerido")
    @Size(max = 10, message = "El numero de colegiatura debe tener 10 caracteres")
    private String numColegiatura;

    @NotBlank(message = "La disponibilidad es requerida")
    private String disponibilidad;

    @Size(max = 500, message = "La descripcion no puede exceder los 500 caracteres")
    private String descripcion;

    @NotNull(message = "La tarifa es requerida")
    private Float tarifa;

    @NotNull(message = "La especialidad es requerida")
    private Especialidad especialidad;

}
