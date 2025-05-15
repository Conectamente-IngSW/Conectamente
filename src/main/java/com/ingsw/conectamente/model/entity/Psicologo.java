package com.ingsw.conectamente.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import com.ingsw.conectamente.model.enums.Especialidad;
import lombok.Data;

@Data
@Entity
@Table(name = "Psicologo")
public class Psicologo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPsicologo",nullable=false)
    private int idPsicologo;
    @Column(name = "numColegiatura", nullable = false, length = 10)
    private String numColegiatura;
    @Column(name = "disponibilidad", nullable = false)
    private int disponibilidad;
    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;
    @Column(name = "tarifa", nullable = false, precision = 4, scale = 2)
    private double tarifa;

    @OneToOne
    @JoinColumn(name = "Usuario_idUsuario", nullable = false)
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "Especialidad_idEspecialidad", nullable = false)
    private Especialidad especialidad;

}
