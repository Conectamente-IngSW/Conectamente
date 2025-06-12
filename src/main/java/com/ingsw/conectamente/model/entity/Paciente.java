package com.ingsw.conectamente.model.entity;

import jakarta.persistence.*;
        import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Data
@Entity
@Table(name = "Paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPaciente;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "dni", nullable = false, unique = true)
    private String dni;

    @Column(name = "edad", nullable = false)
    private Integer edad;

    @Column(name = "descripcionPaciente", columnDefinition = "TEXT")
    private String descripcionPaciente;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    //FK
    //@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario",
    //        foreignKey = @ForeignKey(name = "Usuario_idUsuario" ))
    //private Usuario Usuario_idUsuario;

    @ManyToOne
    @JoinColumn(name = "idDireccion", referencedColumnName = "idDireccion",
            foreignKey = @ForeignKey(name = "Direccion_idDireccion" ))
    private Direccion Direccion_idDireccion;


    @OneToOne
    @JoinColumn(name = "usuario_idUsuario", referencedColumnName = "idUsuario")
    private Usuario usuario;



    //@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Calificacion> calificaciones;

    //NO BORRAR, descomentar cuando esté implementado junto a Citas
    //@OneToMany(mappedBy = "cita", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Cita> citas;
}
