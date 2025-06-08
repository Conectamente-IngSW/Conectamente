package com.ingsw.conectamente.model.entity;

import com.ingsw.conectamente.enums.Especialidad;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Data
@Entity
@Table(name = "Psicologo")
public class Psicologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPsicologo;
    @Column(nullable = false)
    private String numColegiatura;

    private String disponibilidad;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private Float tarifa;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    //FK
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario",
            foreignKey = @ForeignKey(name = "Usuario_idUsuario"))
    private Usuario Usuario_idUsuario;

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

}
