package com.ingsw.conectamente.model.entity;

import com.ingsw.conectamente.enums.Especialidad;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Psicologo")
public class Psicologo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPsicologo;
    @Column(nullable = false)
    private String numColegiatura;

    private String disponibilidad;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private Float tarifa;

    //FK
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario",
            foreignKey = @ForeignKey(name = "Usuario_idUsuario" ))
    private Usuario Usuario_idUsuario;

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
}
