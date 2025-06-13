package com.ingsw.conectamente.model.entity;

import jakarta.persistence.*;
import lombok.Data;

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
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", foreignKey = @ForeignKey(name = "idMensaje_Usuario"))
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conversacion", referencedColumnName = "idConversacion", foreignKey = @ForeignKey(name = "FK_mensaje_conversacion"))
    private Conversacion conversacion;
}

