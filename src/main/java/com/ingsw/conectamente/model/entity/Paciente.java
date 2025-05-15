package com.ingsw.conectamente.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPaciente", nullable = false)
    private int idPaciente;

    @Column(name = "descripcionPaciente", nullable = false, length = 500)
    private String descripcionPaciente;

    @OneToOne
    @JoinColumn(name = "Usuario_idUsuario", nullable = false)
    private Usuario usuario;
}
