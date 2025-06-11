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

    @Column
    private LocalDateTime fechaEnvio;

    //FK
    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario",
            foreignKey = @ForeignKey(name = "Usuario_idUsuario"))
    private Usuario Usuario_idUsuario;
}
