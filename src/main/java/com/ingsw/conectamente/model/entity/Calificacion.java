package com.ingsw.conectamente.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
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
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "idPsicologo", referencedColumnName = "idPsicologo",
            foreignKey = @ForeignKey(name = "Psicologo_idPsicologo" ))
    private Psicologo psicologo;
}
