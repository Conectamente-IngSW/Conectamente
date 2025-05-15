package com.ingsw.conectamente.model.entity;

import com.ingsw.conectamente.enums.Departamento;
import com.ingsw.conectamente.enums.Distrito;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Direccion")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDireccion")
    private int idDireccion;

    @Column(name = "nombreDireccion", nullable = false, length = 100)
    private String nombreDireccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "departamento", nullable = false, length = 50)
    private Departamento departamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "distrito", nullable = false, length = 50)
    private Distrito distrito;

}
