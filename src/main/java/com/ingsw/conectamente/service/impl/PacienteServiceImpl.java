package com.ingsw.conectamente.service.impl;

import com.ingsw.conectamente.dto.PacienteDTO;
import com.ingsw.conectamente.dto.VisualizarPacienteDTO;
import com.ingsw.conectamente.exception.BadRequestException;
import com.ingsw.conectamente.exception.ResourceNotFoundException;
import com.ingsw.conectamente.mapper.PacienteMapper;
import com.ingsw.conectamente.mapper.VisualizacionPacienteMapper;
import com.ingsw.conectamente.model.entity.Paciente;
import com.ingsw.conectamente.model.entity.Usuario;
import com.ingsw.conectamente.repository.PacienteRepository;
import com.ingsw.conectamente.repository.UsuarioRepository;
import com.ingsw.conectamente.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {
    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;
    private final VisualizacionPacienteMapper visualizarPacienteMapper;
    private final UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    @Override
    public List<VisualizarPacienteDTO> getAll() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes.stream().map(visualizarPacienteMapper::toDto).toList();
    }

    @Transactional
    @Override
    public PacienteDTO create(PacienteDTO pacienteDTO) {
        List<Paciente> existentes = pacienteRepository.findByDni(pacienteDTO.getDni());
        if (!existentes.isEmpty()) {
            throw new BadRequestException("Ya existe un paciente registrado con el mismo dni");
        }


        Paciente paciente = pacienteMapper.toEntity(pacienteDTO);
        paciente.setCreatedAt(LocalDateTime.now());
        paciente = pacienteRepository.save(paciente);
        return pacienteMapper.toDto(paciente);
    }

    @Override
    public VisualizarPacienteDTO findById(Integer id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El paciente con ID "+id+" no fue encontrado"));
        return visualizarPacienteMapper.toDto(paciente);
    }

    @Transactional
    @Override
    public PacienteDTO update(Integer id, PacienteDTO updatePacienteDTO) {
        Paciente pacienteFromDb = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El paciente con ID " + id + " no fue encontrado"));

        Usuario usuario = pacienteFromDb.getUsuario();

        // Actualizar los campos
        pacienteFromDb.setNombre(updatePacienteDTO.getNombre());
        pacienteFromDb.setApellido(updatePacienteDTO.getApellido());
        pacienteFromDb.setDni(updatePacienteDTO.getDni());
        pacienteFromDb.setEdad(updatePacienteDTO.getEdad());
        pacienteFromDb.setDescripcionPaciente(updatePacienteDTO.getDescripcion());
        pacienteFromDb.setUpdatedAt(LocalDateTime.now());

        // Datos del usuario
        //usuario.setEmail(updatePacienteDTO.getEmail());
        //usuario.setContrasenia(updatePacienteDTO.getContrasenia());

        // Guardar cambios
        Paciente pacienteActualizado=pacienteRepository.save(pacienteFromDb);
        usuarioRepository.save(usuario);

        // Mapear y devolver DTO con email y contraseña incluidos
        //PacienteDTO dto = pacienteMapper.toDto(pacienteFromDb);
        //dto.setEmail(usuario.getEmail());
        //dto.setContrasenia(usuario.getContrasenia());
        //return dto;
        return pacienteMapper.toDto(pacienteActualizado);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El paciente con ID " + id + " no fue encontrado"));
        pacienteRepository.delete(paciente);
    }
}
