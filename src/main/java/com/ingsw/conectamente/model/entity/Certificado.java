package com.ingsw.conectamente.model.entity;


import jakarta.persistence.*;
import lombok.Generated;

import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name="certificados")
public class Certificados {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String nombreArchivo;
    private String rutaArchivo;

    @ManyToOne
    @JoinColumn(name = "idPsicologo",referencedColumnName = "idPsicologo", foreignKey = @ForeignKey(name = "FK_idPsicologo"))
    private Psicologo Psicologo;

    @Generated
    public Certificados() {}

    @Generate
}
