package com.ingsw.conectamente.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensajeDTO {
    private Integer idMensaje;
    @NotBlank(message = "El mensaje es obligatorio")
    @Size(max=1000, message = "El mensaje puede contener maximo 1000 caracteres")
    private String contenido;
    private LocalDateTime fechaEnvio;
    private Integer usuarioId;
}