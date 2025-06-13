package com.ingsw.conectamente.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ConversacionDTO {
    private Integer idConversacion;
    @NotNull(message = "El id del paciente es obligatorio")
    private Integer pacienteId;
    @NotNull(message = "El id del psicólogo es obligatorio")
    private Integer psicologoId;
    private LocalDateTime fechaCreacion;
    private List<MensajeDTO> mensajes;
}
