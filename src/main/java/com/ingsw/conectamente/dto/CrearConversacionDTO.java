package com.ingsw.conectamente.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CrearConversacionDTO {
    @NotNull
    private Integer pacienteId;

    @NotNull
    private Integer psicologoId;
}