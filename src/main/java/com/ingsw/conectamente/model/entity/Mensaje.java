package com.ingsw.conectamente.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Mensaje")
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMensaje;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    private LocalDateTime fechaEnvio;

    //FK
    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario",
            foreignKey = @ForeignKey(name = "Usuario_idUsuario" ))
    private Usuario Usuario_idUsuario;

    @ManyToOne
    @JoinColumn(name = "idPsicologo", referencedColumnName = "idPsicologo",
            foreignKey = @ForeignKey(name = "Psicologo_idPsicologo" ))
    private Direccion Psicologo_idPsicologo;

    @ManyToOne
    @JoinColumn(name = "idPaciente", referencedColumnName = "idPaciente",
            foreignKey = @ForeignKey(name = "Paciente_idPaciente" ))
    private Direccion Paciente_idPaciente;


}
