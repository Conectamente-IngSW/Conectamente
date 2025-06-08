package com.ingsw.conectamente.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Certificado")
public class Certificado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCertificateFile;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String fileType;

    @Lob
    @Column(nullable = false)
    private byte[] fileData;

    @Column(nullable = false)
    private String uploadDate;

    // FK to Usuario
    @ManyToOne
    @JoinColumn(name = "idPsicologo", referencedColumnName = "idPsicologo",
            foreignKey = @ForeignKey(name = "Psicologo_idPsicologo"))
    private Psicologo psicologo;
}
