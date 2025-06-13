package com.ingsw.conectamente.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "conversaciones")
public class Conversacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConversacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "id_paciente",
            referencedColumnName = "idPaciente",
            foreignKey = @ForeignKey(name = "FK_conversacion_paciente")
    )
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "id_psicologo",
            referencedColumnName = "idPsicologo",
            foreignKey = @ForeignKey(name = "FK_conversacion_psicologo")
    )
    private Psicologo psicologo;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @OneToMany(
            mappedBy = "conversacion",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Mensaje> mensajes = new ArrayList<>();
}
