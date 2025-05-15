package com.ingsw.conectamente.model.entity;

import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.ingsw.conectamente.enums.Especialidad;
import lombok.Data;

import java.util.List;

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
    @Column(name = "tarifa", nullable = false, precision = 10, scale = 2)
    private BigDecimal tarifa;


    @OneToOne
    @JoinColumn(name = "Usuario_idUsuario", nullable = false)
    private Usuario usuario;
    @Enumerated(EnumType.STRING)
    @Column(name = "especialidad", nullable = false, length = 50)
    private Especialidad especialidad;

    @OneToMany(mappedBy = "psicologo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Calificacion> calificaciones;

}
