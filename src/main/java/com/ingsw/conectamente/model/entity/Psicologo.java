package com.ingsw.conectamente.model.entity;

import com.ingsw.conectamente.enums.Especialidad;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name = "edad", nullable = false)
    private Integer edad;

    @Column(name = "numero_colegiatura", nullable = false, unique = true)
    private String numColegiatura;

    @Column(name = "disponibilidad", nullable = false)
    private String disponibilidad;

    @Column(name = "descripcion_psicologo", columnDefinition = "TEXT")
    private String descripcionPsicologo;

    @Column(name = "tarifa", nullable = false)
    private Float tarifa;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    //FK
   // @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario",
    //        foreignKey = @ForeignKey(name = "Usuario_idUsuario" ))
    //private Usuario Usuario_idUsuario;

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @ManyToOne
    @JoinColumn(name = "idDireccion", referencedColumnName = "idDireccion",
            foreignKey = @ForeignKey(name = "Direccion_idDireccion" ))
    private Direccion Direccion_idDireccion;


    @OneToOne
    @JoinColumn(name = "usuario_idUsuario", referencedColumnName = "idUsuario")
    private Usuario usuario;

    //@OneToMany(mappedBy = "psicologo", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Calificacion> calificaciones;

    //NO BORRAR, descomentar cuando esté implementado junto a Citas
    //@OneToMany(mappedBy = "cita", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Cita> citas;

}
