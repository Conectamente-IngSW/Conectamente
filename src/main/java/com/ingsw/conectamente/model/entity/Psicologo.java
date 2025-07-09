package com.ingsw.conectamente.model.entity;

import com.ingsw.conectamente.enums.Departamento;
import com.ingsw.conectamente.enums.Especialidad;
import com.ingsw.conectamente.enums.Modalidad;
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

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "dni", nullable = false)
    private String dni;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "numero_colegiatura", nullable = false, unique = true)
    private String numColegiatura;

    @Column(name = "disponibilidad")
    private String disponibilidad;

    @Column(name = "descripcion_psicologo", columnDefinition = "TEXT")
    private String descripcionPsicologo;

    @Column(name = "tarifa")
    private Float tarifa;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "direccion", columnDefinition = "TEXT")
    private String direccion;

    @Enumerated(EnumType.STRING)
    private Departamento departamento;

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @Enumerated(EnumType.STRING)
    private Modalidad modalidad;

    @ManyToOne
    @JoinColumn(name = "idDireccion", referencedColumnName = "idDireccion",
            foreignKey = @ForeignKey(name = "Direccion_idDireccion" ))
    private Direccion Direccion_idDireccion;

    @OneToOne
    @JoinColumn(name = "usuario_idUsuario", referencedColumnName = "idUsuario")
    private Usuario usuario;
}
