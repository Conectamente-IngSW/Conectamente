package com.ingsw.conectamente.service;

import com.ingsw.conectamente.dto.CalificacionDTO;
import com.ingsw.conectamente.exception.ResourceNotFoundException;
import com.ingsw.conectamente.mapper.CalificacionMapper;
import com.ingsw.conectamente.model.entity.Calificacion;
import com.ingsw.conectamente.repository.CalificacionRepository;
import com.ingsw.conectamente.service.impl.CalificacionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CalificacionServerUnitTest {
    @Mock
    private CalificacionRepository calificacionRepository;

    @Mock
    private CalificacionMapper calificacionMapper;

    @InjectMocks
    private CalificacionServiceImpl calificacionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCalificacion_success() {
        CalificacionDTO dto = new CalificacionDTO();
        dto.setPuntaje(5);
        dto.setComentario("Excelente");
        dto.setPacienteId(1);
        dto.setPsicologoId(1);

        Calificacion entity = new Calificacion();
        entity.setPuntaje(dto.getPuntaje());
        entity.setComentario(dto.getComentario());

        when(calificacionMapper.toEntity(dto)).thenReturn(entity);
        when(calificacionRepository.save(any())).thenAnswer(inv -> {
            Calificacion saved = inv.getArgument(0);
            saved.setIdCalificacion(1);
            return saved;
        });
        when(calificacionMapper.toDto(any())).thenReturn(dto);

        CalificacionDTO result = calificacionService.createCalificacion(dto);

        assertNotNull(result);
        verify(calificacionRepository).save(any());
    }

    @Test
    void findCalificacionById_found() {
        Calificacion entity = new Calificacion();
        entity.setIdCalificacion(1);
        CalificacionDTO dto = new CalificacionDTO();
        dto.setIdCalificacion(1);

        when(calificacionRepository.findById(1)).thenReturn(Optional.of(entity));
        when(calificacionMapper.toDto(entity)).thenReturn(dto);

        CalificacionDTO result = calificacionService.findCalificacionById(1);

        assertEquals(1, result.getIdCalificacion());
        verify(calificacionRepository).findById(1);
    }

    @Test
    void findCalificacionById_notFound() {
        when(calificacionRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> calificacionService.findCalificacionById(1));
    }

    @Test
    void findCalificacionesByPsicologoId_success() {
        List<Calificacion> calificaciones = List.of(new Calificacion(), new Calificacion());
        when(calificacionRepository.findByPsicologoIdPsicologo(1)).thenReturn(calificaciones);
        when(calificacionMapper.toDto(any())).thenReturn(new CalificacionDTO());

        List<CalificacionDTO> result = calificacionService.findCalificacionesByPsicologoId(1);

        assertEquals(2, result.size());
    }

    @Test
    void updateCalificacion_success() {
        CalificacionDTO dto = new CalificacionDTO();
        dto.setPuntaje(4);
        dto.setComentario("Muy bueno");

        Calificacion existing = new Calificacion();
        existing.setIdCalificacion(1);
        existing.setPuntaje(3);
        existing.setComentario("Regular");

        when(calificacionRepository.findById(1)).thenReturn(Optional.of(existing));
        when(calificacionRepository.save(any())).thenReturn(existing);
        when(calificacionMapper.toDto(existing)).thenReturn(dto);

        CalificacionDTO result = calificacionService.update(1, dto);

        assertEquals(4, result.getPuntaje());
        assertEquals("Muy bueno", result.getComentario());
    }

    @Test
    void updateCalificacion_notFound() {
        CalificacionDTO dto = new CalificacionDTO();
        dto.setPuntaje(5);

        when(calificacionRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> calificacionService.update(1, dto));
    }

    @Test
    void deleteCalificacion_success() {
        Calificacion calificacion = new Calificacion();
        calificacion.setIdCalificacion(1);

        when(calificacionRepository.findById(1)).thenReturn(Optional.of(calificacion));

        assertDoesNotThrow(() -> calificacionService.delete(1));
        verify(calificacionRepository).delete(calificacion);
    }

    @Test
    void deleteCalificacion_notFound() {
        when(calificacionRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> calificacionService.delete(1));
    }


}
