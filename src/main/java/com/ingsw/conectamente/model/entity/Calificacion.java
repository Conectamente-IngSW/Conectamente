package com.ingsw.conectamente.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name="Calificacion")
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCalificacion", nullable = false)
    private int idCalificacion;

    @Column(name = "puntaje", nullable = false)
    private int puntaje;

    @Column(name = "comentario", nullable = false, length = 500)
    private String comentario;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "Psicologo_idPsicologo", nullable = false)
    private Psicologo psicologo;

    @ManyToOne
    @JoinColumn(name = "Paciente_idPaciente", nullable = false)
    private Paciente paciente;
}
