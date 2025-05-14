package com.ingsw.conectamente.service;
import com.ingsw.conectamente.model.entity.Paciente;

public interface PacienteService {
    Paciente create(Paciente paciente);
    Paciente findById(Integer id);
    Paciente update(Integer id, Paciente updatedUsuario);
}
