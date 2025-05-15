package com.ingsw.conectamente.service.impl;

import com.ingsw.conectamente.model.entity.Paciente;
import com.ingsw.conectamente.model.entity.Usuario;
import com.ingsw.conectamente.repository.PacienteRepository;
import com.ingsw.conectamente.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    @Transactional
    @Override
    public Paciente create(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente findById(Integer id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }

    @Transactional
    @Override
    public Paciente update(Integer id, Paciente updatedPaciente) {
        Paciente pacienteFromDb = findById(id);
        pacienteFromDb.setDescripcionPaciente(updatedPaciente.getDescripcionPaciente());

        Usuario usuarioFromDb = pacienteFromDb.getUsuario_idUsuario();
        Usuario updatedUsuario = updatedPaciente.getUsuario_idUsuario();

        if (updatedUsuario.getNombre() != null) {
            usuarioFromDb.setNombre(updatedUsuario.getNombre());
        }
        if (updatedUsuario.getApellido() != null) {
            usuarioFromDb.setApellido(updatedUsuario.getApellido());
        }
        if (updatedUsuario.getEmail() != null) {
            usuarioFromDb.setEmail(updatedUsuario.getEmail());
        }
        if (updatedUsuario.getEdad() != null) {
            usuarioFromDb.setEdad(updatedUsuario.getEdad());
        }
        if (updatedUsuario.getDni() != null) {
            usuarioFromDb.setDni(updatedUsuario.getDni());
        }
        if (updatedUsuario.getContrasenia() != null) {
            usuarioFromDb.setContrasenia(updatedUsuario.getContrasenia());
        }

        return pacienteRepository.save(pacienteFromDb);
    }
}
