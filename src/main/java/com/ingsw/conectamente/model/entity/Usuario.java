package com.ingsw.conectamente.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private Integer edad;

    @Column(nullable = false)
    private String dni;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String contrasenia;

    //FK
    @ManyToOne
    @JoinColumn(name = "idDireccion", referencedColumnName = "idDireccion",
    foreignKey = @ForeignKey(name = "Direccion_idDireccion" ))
    private Direccion Direccion_idDireccion;

}
