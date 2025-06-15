package com.ingsw.conectamente.service;

import com.ingsw.conectamente.dto.CitaDTO;
import com.ingsw.conectamente.exception.ResourceNotFoundException;
import com.ingsw.conectamente.mapper.CitaMapper;
import com.ingsw.conectamente.model.entity.Cita;
import com.ingsw.conectamente.model.entity.Paciente;
import com.ingsw.conectamente.model.entity.Psicologo;
import com.ingsw.conectamente.repository.CitaRepository;
import com.ingsw.conectamente.repository.PacienteRepository;
import com.ingsw.conectamente.repository.PsicologoRepository;
import com.ingsw.conectamente.service.impl.CitaServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CitaServiceUnitTest {

    @Mock
    private CitaRepository citaRepository;

    @Mock
    private CitaMapper citaMapper;
    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private PsicologoRepository psicologoRepository;

    @InjectMocks
    private CitaServiceImpl citaService;

    private AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        if (mocks != null) {
            mocks.close(); // <-- Close after each test
        }
    }

    private CitaDTO ejemploDto() {
        CitaDTO dto = new CitaDTO();
        dto.setIdCita(1);
        dto.setFechaCita(LocalDate.of(2025, 6, 20));
        dto.setHoraCita(LocalTime.of(14, 30));
        dto.setDescripcion("Sesión inicial");
        dto.setIdPaciente(10);
        dto.setIdPsicologo(5);
        return dto;
    }

    private Cita ejemploEntity() {
        Cita e = new Cita();
        e.setIdCita(1);
        e.setFechaCita(LocalDate.of(2025, 6, 20));
        e.setHoraCita(LocalTime.of(14, 30));
        e.setDescripcion("Sesión inicial");
        // asumimos que Cita tiene setPaciente y setPsicologo, pero no hacemos foco en ellos aquí
        return e;
    }

    @Test
    @DisplayName("Crear cita con éxito")
    void createCita_success() {

        CitaDTO dtoIn = ejemploDto();
        dtoIn.setIdCita(null); // La cita nueva no lleva id

        Paciente paciente = new Paciente();
        paciente.setIdPaciente(dtoIn.getIdPaciente());
        Psicologo psicologo = new Psicologo();
        psicologo.setIdPsicologo(dtoIn.getIdPsicologo());


        when(pacienteRepository.findById(eq(dtoIn.getIdPaciente())))
                .thenReturn(Optional.of(paciente));
        when(psicologoRepository.findById(eq(dtoIn.getIdPsicologo())))
                .thenReturn(Optional.of(psicologo));

        Cita entitySinId = ejemploEntity();
        entitySinId.setIdCita(null);
        Cita entityConId = ejemploEntity();
        entityConId.setIdCita(1);

        when(citaMapper.toEntity(dtoIn)).thenReturn(entitySinId);
        when(citaRepository.save(entitySinId)).thenReturn(entityConId);
        when(citaMapper.toDto(entityConId)).thenReturn(ejemploDto());

        CitaDTO result = citaService.createCita(dtoIn);

        // Assert del resultado
        assertNotNull(result);
        assertEquals(1, result.getIdCita());
        verify(citaRepository).save(entitySinId);
    }


    @Test
    @DisplayName("Buscar cita por ID con éxito")
    void findCitaById_found() {
        Cita entity = ejemploEntity();
        CitaDTO dto = ejemploDto();

        when(citaRepository.findById(1)).thenReturn(Optional.of(entity));
        when(citaMapper.toDto(entity)).thenReturn(dto);

        CitaDTO result = citaService.findCitaById(1);

        assertEquals(1, result.getIdCita());
        verify(citaRepository).findById(1);
    }

    @Test
    @DisplayName("Buscar cita por ID no encontrada")
    void findCitaById_notFound() {
        when(citaRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> citaService.findCitaById(1));
    }

    @Test
    @DisplayName("Buscar citas por ID de psicólogo")
    void findCitasByPsicologoId_success() {
        Cita e1 = ejemploEntity();
        Cita e2 = ejemploEntity();
        when(citaRepository.findByPsicologoIdPsicologo(5)).thenReturn(List.of(e1, e2));
        when(citaMapper.toDto(any(Cita.class))).thenReturn(ejemploDto());

        List<CitaDTO> result = citaService.findCitaByPsicologoId(5);

        assertEquals(2, result.size());
        verify(citaRepository).findByPsicologoIdPsicologo(5);
    }

    @Test
    @DisplayName("Buscar citas por ID de paciente")
    void findCitasByPacienteId_success() {
        Cita e1 = ejemploEntity();
        when(citaRepository.findByPacienteIdPaciente(10)).thenReturn(List.of(e1));
        when(citaMapper.toDto(e1)).thenReturn(ejemploDto());

        List<CitaDTO> result = citaService.findCitaByPacienteId(10);

        assertEquals(1, result.size());
        verify(citaRepository).findByPacienteIdPaciente(10);
    }

    @Test
    @DisplayName("Actualizar cita con éxito")
    void updateCita_success() {
        CitaDTO dtoIn = ejemploDto();
        dtoIn.setDescripcion("Sesión actualizada");
        Cita existing = ejemploEntity();

        // MOCKS necesarios:
        when(citaRepository.findById(1)).thenReturn(Optional.of(existing));
        // Mock para paciente:
        when(pacienteRepository.findById(eq(dtoIn.getIdPaciente())))
                .thenReturn(Optional.of(new Paciente()));
        // Mock para psicólogo (si tu update también lo consulta):
        when(psicologoRepository.findById(eq(dtoIn.getIdPsicologo())))
                .thenReturn(Optional.of(new Psicologo()));
        // Mocks habituales
        when(citaRepository.save(existing)).thenReturn(existing);
        when(citaMapper.toDto(existing)).thenReturn(dtoIn);

        CitaDTO result = citaService.update(1, dtoIn);

        assertEquals("Sesión actualizada", result.getDescripcion());
        verify(citaRepository).save(existing);
    }

    @Test
    @DisplayName("Actualizar cita no encontrada")
    void updateCita_notFound() {
        CitaDTO dtoIn = ejemploDto();
        when(citaRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> citaService.update(1, dtoIn));
    }

    @Test
    @DisplayName("Eliminar cita con éxito")
    void deleteCita_success() {
        Cita entity = ejemploEntity();
        when(citaRepository.findById(1)).thenReturn(Optional.of(entity));

        assertDoesNotThrow(() -> citaService.delete(1));
        verify(citaRepository).delete(entity);
    }

    @Test
    @DisplayName("Eliminar cita no encontrada")
    void deleteCita_notFound() {
        when(citaRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> citaService.delete(1));
    }
}
