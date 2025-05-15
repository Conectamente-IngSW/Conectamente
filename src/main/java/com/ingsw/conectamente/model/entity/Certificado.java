package com.ingsw.conectamente.model.entity;


import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name="certificado")
@Data
public class Certificado {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long idCertificado;
    @Column(nullable = true)
    private String nombreArchivo;
    @Column(nullable = true)
    private String rutaArchivo;

    //FK
    @ManyToOne
    @JoinColumn(name = "idPsicologo", referencedColumnName = "idPsicologo", foreignKey = @ForeignKey(name = "FK_idPsicologo"))
    private Psicologo psicologo;



}


