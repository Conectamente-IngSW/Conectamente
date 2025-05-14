package com.ingsw.conectamente.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPaciente;

    @Column(columnDefinition = "TEXT")
    private String descripcionPaciente;

    //FK
    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario",
            foreignKey = @ForeignKey(name = "Usuario_idUsuario" ))
    private Usuario Usuario_idUsuario;
}
