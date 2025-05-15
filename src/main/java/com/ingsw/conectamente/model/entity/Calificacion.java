package com.ingsw.conectamente.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Calificacion")
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCalificacion;

    private Integer puntaje;

    @Column(columnDefinition = "TEXT")
    private String comentario;

    private LocalDateTime fecha;

    //FK
    @ManyToOne
    @JoinColumn(name = "idPaciente", referencedColumnName = "idPaciente",
            foreignKey = @ForeignKey(name = "Paciente_idPaciente" ))
    private Paciente Paciente_idPaciente;

    @ManyToOne
    @JoinColumn(name = "idPsicologo", referencedColumnName = "idPsicologo",
            foreignKey = @ForeignKey(name = "Psicologo_idPsicologo" ))
    private Psicologo Psicologo_idPsicologo;
}
