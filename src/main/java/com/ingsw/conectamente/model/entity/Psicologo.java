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
    private String nombrePsicologo;

    @Column(nullable = false)
    private String apellidoPsicologo;

    @Column(nullable = false)
    private String dniPsicologo;

    @Column(nullable = false)
    private Integer edadPsicologo;

    @Column(nullable = false)
    private String numColegiatura;

    private String disponibilidad;

    @Column(columnDefinition = "TEXT")
    private String descripcionPsicologo;

    private Float tarifa;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    //FK
    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario",
            foreignKey = @ForeignKey(name = "Usuario_idUsuario" ))
    private Usuario Usuario_idUsuario;

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @ManyToOne
    @JoinColumn(name = "idDireccion", referencedColumnName = "idDireccion",
            foreignKey = @ForeignKey(name = "Direccion_idDireccion" ))
    private Direccion Direccion_idDireccion;

}
