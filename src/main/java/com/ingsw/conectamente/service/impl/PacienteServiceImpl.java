package com.ingsw.conectamente.service.impl;

import com.ingsw.conectamente.dto.PacienteDTO;
import com.ingsw.conectamente.enums.Rol;
import com.ingsw.conectamente.exception.BadRequestException;
import com.ingsw.conectamente.exception.ResourceNotFoundException;
import com.ingsw.conectamente.mapper.PacienteMapper;
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
    private final UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    @Override
    public List<PacienteDTO> getAll() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes.stream().map(pacienteMapper::toDto).toList();
    }

    @Transactional
    @Override
    public PacienteDTO create(PacienteDTO pacienteDTO) {
        List<Paciente> existentes = pacienteRepository.findByIdPaciente(pacienteDTO.getIdPaciente());
        if (!existentes.isEmpty()) {
            throw new BadRequestException("Ya existe un paciente con el mismo id");
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(pacienteDTO.getEmail());
        usuario.setContrasenia(pacienteDTO.getContrasenia());
        usuario.setRol(Rol.PACIENTE);
        usuario = usuarioRepository.save(usuario);

        Paciente paciente = pacienteMapper.toEntity(pacienteDTO);
        paciente.setUsuario_idUsuario(usuario);
        paciente.setCreatedAt(LocalDateTime.now());
        paciente = pacienteRepository.save(paciente);
        return pacienteMapper.toDto(paciente);
    }

    @Override
    public PacienteDTO findById(Integer id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El paciente con ID "+id+" no fue encontrado"));
        return pacienteMapper.toDto(paciente);
    }

    @Transactional
    @Override
    public PacienteDTO update(Integer id, PacienteDTO updatePacienteDTO) {
        Paciente pacienteFromDb = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El paciente con ID " + id + " no fue encontrado"));

        // Actualizar los campos
        pacienteFromDb.setNombrePaciente(updatePacienteDTO.getNombre());
        pacienteFromDb.setApellidoPaciente(updatePacienteDTO.getApellido());
        pacienteFromDb.setDniPaciente(updatePacienteDTO.getDniPaciente());
        pacienteFromDb.setEdad(updatePacienteDTO.getEdad());
        pacienteFromDb.setDescripcionPaciente(updatePacienteDTO.getDescripcion());
        pacienteFromDb.setUpdatedAt(LocalDateTime.now());

        pacienteFromDb = pacienteRepository.save(pacienteFromDb);
        return pacienteMapper.toDto(pacienteFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El paciente con ID " + id + " no fue encontrado"));
        pacienteRepository.delete(paciente);
    }
}
