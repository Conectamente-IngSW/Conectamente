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

    @Column(nullable = false)
    private String nombrePaciente;

    @Column(nullable = false)
    private String apellidoPaciente;

    @Column(nullable = false)
    private String dniPaciente;

    @Column(nullable = false)
    private Integer edad;

    @Column(columnDefinition = "TEXT")
    private String descripcionPaciente;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    //FK
    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario",
            foreignKey = @ForeignKey(name = "Usuario_idUsuario" ))
    private Usuario Usuario_idUsuario;

    @ManyToOne
    @JoinColumn(name = "idDireccion", referencedColumnName = "idDireccion",
            foreignKey = @ForeignKey(name = "Direccion_idDireccion" ))
    private Direccion Direccion_idDireccion;

    @OneToOne
    @JoinColumn(name = "usuario_idUsuario", referencedColumnName = "idUsuario")
    private Usuario usuario;


}
