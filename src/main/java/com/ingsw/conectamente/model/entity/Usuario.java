package com.ingsw.conectamente.model.entity;

import com.ingsw.conectamente.enums.Rol;
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
    private String email;

    @Column(nullable = false)
    private String contrasenia;

    //FK
    @Enumerated(EnumType.STRING)
    private Rol rol;

}
