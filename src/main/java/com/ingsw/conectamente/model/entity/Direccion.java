package com.ingsw.conectamente.model.entity;

import com.ingsw.conectamente.model.enums.DepartamentoEnum;
import com.ingsw.conectamente.model.enums.DistritoEnum;
import jakarta.persistence.Entity;
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
    private DepartamentoEnum departamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "distrito", nullable = false, length = 50)
    private DistritoEnum distrito;

}
