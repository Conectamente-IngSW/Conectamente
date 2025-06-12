package com.ingsw.conectamente.service.impl;

import com.ingsw.conectamente.dto.CalificacionDTO;
import com.ingsw.conectamente.exception.ResourceNotFoundException;
import com.ingsw.conectamente.mapper.CalificacionMapper;
import com.ingsw.conectamente.model.entity.Calificacion;
import com.ingsw.conectamente.repository.CalificacionRepository;
import com.ingsw.conectamente.service.CalificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalificacionServiceImpl implements CalificacionService {
    private final CalificacionRepository calificacionRepository;
    private final CalificacionMapper calificacionMapper;

    @Transactional
    @Override
    public CalificacionDTO createCalificacion(CalificacionDTO calificacionDTO) {
        Calificacion calificacion = calificacionMapper.toEntity(calificacionDTO);
        calificacion.setFecha(LocalDateTime.now());
        calificacion = calificacionRepository.save(calificacion);
        return calificacionMapper.toDto(calificacion);
    }

    @Override
    public CalificacionDTO findCalificacionById(Integer id) {
        Calificacion calificacion = calificacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La calificación con ID "+id+" no fue encontrado"));
        return calificacionMapper.toDto(calificacion);
    }

    @Override
    public List<CalificacionDTO> findCalificacionesByPsicologoId(Integer idPsicologo) {
        List<Calificacion> calificaciones = calificacionRepository.findByPsicologoIdPsicologo(idPsicologo);
        return calificaciones.stream()
                .map(calificacionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CalificacionDTO update(Integer id, CalificacionDTO updateCalificacionDTO) {
        Calificacion calificacionFromDb = calificacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La calificación con ID " + id + " no fue encontrada"));

        if (updateCalificacionDTO.getPuntaje() != null) {
            calificacionFromDb.setPuntaje(updateCalificacionDTO.getPuntaje());
        }
        if (updateCalificacionDTO.getComentario() != null) {
            calificacionFromDb.setComentario(updateCalificacionDTO.getComentario());
        }
        calificacionFromDb.setFecha(LocalDateTime.now());
        calificacionFromDb = calificacionRepository.save(calificacionFromDb);
        return calificacionMapper.toDto(calificacionFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Calificacion calificacion = calificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La calificacion con ID " + id + " no fue encontrado"));
        calificacionRepository.delete(calificacion);

    }
}
