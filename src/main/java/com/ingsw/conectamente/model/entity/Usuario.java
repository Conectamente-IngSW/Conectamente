package com.ingsw.conectamente.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;
    @Column(name = "edad", nullable = false)
    private int edad;
    @Column(name = "dni", nullable = false, unique = true, length = 15)
    private String dni;
    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;
    @Column(name = "contrasenia", nullable = false, length = 30)
    private String contrasenia;
    @ManyToOne
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;

}
