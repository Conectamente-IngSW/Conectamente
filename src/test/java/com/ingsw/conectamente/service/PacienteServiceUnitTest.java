package com.ingsw.conectamente.service;

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
import com.ingsw.conectamente.service.impl.PacienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

public class PacienteServiceUnitTest {
    @Mock
    private PacienteRepository pacienteRepository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private PacienteMapper pacienteMapper;
    @Mock
    private VisualizacionPacienteMapper visualizacionPacienteMapper;

    @InjectMocks
    private PacienteServiceImpl pacienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("CP01: Crear paciente con éxito")
    void createPaciente_success() {
        PacienteDTO dto = new PacienteDTO();
        dto.setIdPaciente(6);
        dto.setNombre("Ana");
        dto.setApellido("Pérez");
        dto.setDni("12345678");
        dto.setEdad(25);
        dto.setDescripcion("Paciente con ansiedad");

        when(pacienteRepository.findByIdPaciente(1)).thenReturn(List.of());
        when(usuarioRepository.save(any())).thenAnswer(inv -> {
            Usuario u = inv.getArgument(0);
            u.setIdUsuario(10);
            return u;
        });

        Paciente paciente = new Paciente();
        when(pacienteMapper.toEntity(dto)).thenReturn(paciente);
        when(pacienteRepository.save(any())).thenReturn(paciente);
        when(pacienteMapper.toDto(paciente)).thenReturn(dto);

        PacienteDTO result = pacienteService.create(dto);

        assertNotNull(result);
        verify(pacienteRepository).save(any());
    }

    @Test
    @DisplayName("CP02: No se debe crear paciente duplicado")
    void createPaciente_duplicateId() {
        PacienteDTO dto = new PacienteDTO();
        dto.setDni("12345678"); // este DNI ya existe

        when(pacienteRepository.findByDni("12345678")).thenReturn(List.of(new Paciente()));

        assertThrows(BadRequestException.class, () -> pacienteService.create(dto));
    }

    @Test
    @DisplayName("CP03: Buscar paciente por ID con éxito")
    void findPacienteById_success() {
        Paciente paciente = new Paciente();
        paciente.setIdPaciente(1);
        paciente.setNombre("Ana");
        paciente.setApellido("Lopez");

        VisualizarPacienteDTO dto = new VisualizarPacienteDTO();
        dto.setNombre("Ana");
        dto.setApellido("Lopez");

        when(pacienteRepository.findById(1)).thenReturn(Optional.of(paciente));
        when(visualizacionPacienteMapper.toDto(paciente)).thenReturn(dto);

        VisualizarPacienteDTO result = pacienteService.findById(1);

        assertEquals("Ana", result.getNombre());
        assertEquals("Lopez", result.getApellido());
        verify(pacienteRepository).findById(1);
    }

    @Test
    @DisplayName("CP04: Buscar paciente por ID no encontrado")
    void findPacienteById_notFound() {
        when(pacienteRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> pacienteService.findById(1));
    }

    @Test
    @DisplayName("CP05: Obtener todos los pacientes")
    void getAllPacientes_success() {
        List<Paciente> pacientes = List.of(new Paciente(), new Paciente());
        when(pacienteRepository.findAll()).thenReturn(pacientes);
        when(visualizacionPacienteMapper.toDto(any())).thenReturn(new VisualizarPacienteDTO());

        List<VisualizarPacienteDTO> result = pacienteService.getAll();

        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("CP06: Actualizar paciente con éxito")
    void updatePaciente_success() {
        Paciente existing = new Paciente();
        existing.setIdPaciente(1);

        PacienteDTO dto = new PacienteDTO();
        dto.setNombre("Luis");
        dto.setApellido("Gomez");
        dto.setDni("87654321");
        dto.setEdad(30);
        dto.setDescripcion("Paciente actualizado");

        when(pacienteRepository.findById(1)).thenReturn(Optional.of(existing));
        when(pacienteRepository.save(any())).thenReturn(existing);
        when(pacienteMapper.toDto(existing)).thenReturn(dto);

        PacienteDTO result = pacienteService.update(1, dto);

        assertEquals("Luis", result.getNombre());
        assertEquals(30, result.getEdad());
    }

    @Test
    @DisplayName("CP07: Actualizar paciente no encontrado")
    void updatePaciente_notFound() {
        when(pacienteRepository.findById(1)).thenReturn(Optional.empty());

        PacienteDTO dto = new PacienteDTO();
        assertThrows(ResourceNotFoundException.class, () -> pacienteService.update(1, dto));
    }

    @Test
    @DisplayName("CP08: Eliminar paciente con éxito")
    void deletePaciente_success() {
        Paciente paciente = new Paciente();
        paciente.setIdPaciente(1);
        when(pacienteRepository.findById(1)).thenReturn(Optional.of(paciente));

        assertDoesNotThrow(() -> pacienteService.delete(1));
        verify(pacienteRepository).delete(paciente);
    }

    @Test
    @DisplayName("CP09: Eliminar paciente no encontrado")
    void deletePaciente_notFound() {
        when(pacienteRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> pacienteService.delete(1));
    }


}
