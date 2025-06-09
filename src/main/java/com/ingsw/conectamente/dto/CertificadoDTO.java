package com.ingsw.conectamente.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CertificadoDTO {
    private Integer idCertificado; // Opcional, para actualizaciones

    @NotBlank(message = "El nombre del archivo es obligatorio")
    @Size(max = 255, message = "El nombre del archivo debe tener como máximo 255 caracteres")
    private String fileName;

    private String fileType;

    @NotNull(message = "El archivo es obligatorio")
    private byte[] fileData;

    private String uploadDate;

    @NotNull(message = "El ID del psicólogo es obligatorio")
    @Positive(message = "El ID del psicólogo debe ser un número positivo")
    private Integer idPsicologo;
}