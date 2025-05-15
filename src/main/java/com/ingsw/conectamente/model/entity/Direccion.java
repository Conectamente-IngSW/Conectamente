package com.ingsw.conectamente.model.entity;

import com.ingsw.conectamente.enums.Departamento;
import com.ingsw.conectamente.enums.Distrito;
import com.ingsw.conectamente.enums.Especialidad;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Direccion")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDireccion;
    private String nombreDireccion;

    //FK
    @Enumerated(EnumType.STRING)
    private Departamento departamento;

    @Enumerated(EnumType.STRING)
    private Distrito distrito;
}
