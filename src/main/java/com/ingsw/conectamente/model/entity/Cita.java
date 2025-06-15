package com.ingsw.conectamente.model.entity;

import com.ingsw.conectamente.enums.EstadoCita;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Data
@Entity
@Table(name = "Cita")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCita;

    private LocalDate fechaCita;
    private LocalTime horaCita;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    //fecha registro
    private LocalDateTime fecha;

    //FK
    @ManyToOne
    @JoinColumn(name = "idPaciente", referencedColumnName = "idPaciente",
            foreignKey = @ForeignKey(name = "Paciente_idPaciente" ))
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "idPsicologo", referencedColumnName = "idPsicologo",
            foreignKey = @ForeignKey(name = "Psicologo_idPsicologo" ))
    private Psicologo psicologo;

    @Enumerated(EnumType.STRING)
    private EstadoCita estadoCita;

}
