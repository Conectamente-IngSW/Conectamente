package com.ingsw.conectamente.service.impl;

import com.ingsw.conectamente.dto.CitaDTO;
import com.ingsw.conectamente.exception.BadRequestException;
import com.ingsw.conectamente.exception.ResourceNotFoundException;
import com.ingsw.conectamente.mapper.CitaMapper;
import com.ingsw.conectamente.model.entity.Cita;
import com.ingsw.conectamente.model.entity.Paciente;
import com.ingsw.conectamente.model.entity.Psicologo;
import com.ingsw.conectamente.repository.CitaRepository;
import com.ingsw.conectamente.repository.PacienteRepository;
import com.ingsw.conectamente.repository.PsicologoRepository;
import com.ingsw.conectamente.service.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements CitaService {
    private final CitaRepository citaRepository;
    private final CitaMapper citaMapper;
    private final PacienteRepository pacienteRepository;
    private final PsicologoRepository psicologoRepository;

    @Transactional
    @Override
    public CitaDTO createCita(CitaDTO citaDTO) {
        // Combinar fecha y hora en un solo LocalDateTime
        LocalDateTime fechaHoraCita = LocalDateTime.of(citaDTO.getFechaCita(), citaDTO.getHoraCita());

        // Validar que la fecha y hora de la cita no sea en el pasado
        if (fechaHoraCita.isBefore(LocalDateTime.now())) {
            throw new BadRequestException("No se puede crear una cita con fecha y hora en el pasado.");
        }
        Cita cita = citaMapper.toEntity(citaDTO);
        cita.setFecha(LocalDateTime.now());
        cita = citaRepository.save(cita);
        return citaMapper.toDto(cita);
    }

    @Override
    public CitaDTO findCitaById(Integer id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La cita con ID "+id+" no fue encontrado"));
        return citaMapper.toDto(cita);
    }

    @Override
    public List<CitaDTO> findCitaByPsicologoId(Integer idPsicologo) {
        List<Cita> citas = citaRepository.findByPsicologoIdPsicologo(idPsicologo);
        return citas.stream()
                .map(citaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CitaDTO> findCitaByPacienteId(Integer idPaciente) {
        List<Cita> citas = citaRepository.findByPacienteIdPaciente(idPaciente);
        return citas.stream()
                .map(citaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CitaDTO update(Integer id, CitaDTO updateCitaDTO) {
        // Buscar la cita existente
        Cita citaFromDb = citaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La cita con ID " + id + " no fue encontrada"));

        // Buscar el paciente
        Paciente paciente = pacienteRepository.findById(updateCitaDTO.getIdPaciente())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente con ID " + updateCitaDTO.getIdPaciente() + " no encontrado"));

        // Buscar el psicólogo
        Psicologo psicologo = psicologoRepository.findById(updateCitaDTO.getIdPsicologo())
                .orElseThrow(() -> new ResourceNotFoundException("Psicólogo con ID " + updateCitaDTO.getIdPsicologo() + " no encontrado"));

        LocalDateTime nuevaFechaHora = LocalDateTime.of(updateCitaDTO.getFechaCita(), updateCitaDTO.getHoraCita());
        if (nuevaFechaHora.isBefore(LocalDateTime.now())) {
            throw new BadRequestException("No se puede actualizar la cita a una fecha y hora en el pasado.");
        }

        // Actualizar campos
        citaFromDb.setFechaCita(updateCitaDTO.getFechaCita());
        citaFromDb.setHoraCita(updateCitaDTO.getHoraCita());
        citaFromDb.setDescripcion(updateCitaDTO.getDescripcion());
        citaFromDb.setPaciente(paciente);
        citaFromDb.setPsicologo(psicologo);
        citaFromDb.setFecha(LocalDateTime.now()); // para marcar la actualización

        // Guardar
        Cita citaActualizada = citaRepository.save(citaFromDb);
        return citaMapper.toDto(citaActualizada);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La cita con ID " + id + " no fue encontrado"));
        citaRepository.delete(cita);
    }
}
