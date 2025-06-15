package com.ingsw.conectamente.model.entity;

import jakarta.persistence.*;
        import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

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

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "descripcionPaciente", columnDefinition = "TEXT")
    private String descripcionPaciente;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    //FK

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

}
