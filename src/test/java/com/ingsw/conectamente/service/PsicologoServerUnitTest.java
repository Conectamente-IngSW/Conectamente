package com.ingsw.conectamente.service;

import com.ingsw.conectamente.dto.PsicologoDTO;
import com.ingsw.conectamente.enums.Especialidad;
import com.ingsw.conectamente.enums.ERol;
import com.ingsw.conectamente.exception.BadRequestException;
import com.ingsw.conectamente.exception.ResourceNotFoundException;
import com.ingsw.conectamente.mapper.PsicologoMapper;
import com.ingsw.conectamente.mapper.VisualizacionPsicologoMapper;
import com.ingsw.conectamente.model.entity.Psicologo;
import com.ingsw.conectamente.model.entity.Usuario;
import com.ingsw.conectamente.repository.PsicologoRepository;
import com.ingsw.conectamente.repository.UsuarioRepository;
import com.ingsw.conectamente.service.impl.PsicologoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PsicologoServerUnitTest {
    @Mock
    private PsicologoRepository psicologoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PsicologoMapper psicologoMapper;

    @Mock
    private VisualizacionPsicologoMapper visualizacionPsicologoMapper;

    @InjectMocks
    private PsicologoServiceImpl psicologoService;

    private PsicologoDTO dto;
    private Usuario usuarioMock;
    private Psicologo entityMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dto = new PsicologoDTO();
        dto.setNombre("Ana");
        dto.setApellido("Gomez");
        dto.setDni("12345678");
        dto.setEdad(35);
        dto.setNumColegiatura("1234567890");
        dto.setDisponibilidad("Lunes a Viernes");
        dto.setDescripcion("Psicóloga especializada");
        dto.setTarifa(150.0f);
        dto.setEspecialidad(Especialidad.CLINICA);
        //dto.setEmail("ana@example.com");
        //dto.setContrasenia("securepass");

        usuarioMock = new Usuario();
        usuarioMock.setIdUsuario(1);
        //usuarioMock.setEmail(dto.getEmail());
        //usuarioMock.setContrasenia(dto.getContrasenia());
        //usuarioMock.setRol(ERol.PSICOLOGO);

        entityMock = new Psicologo();
        entityMock.setIdPsicologo(10);
    }

    /*
    ///////// USER STORY 02
    @Test
    @DisplayName("CP01 - Crear psicólogo con datos válidos")
    void testCrearPsicologo_exitoso() {
        when(psicologoRepository.findByNumColegiatura(dto.getNumColegiatura()))
                .thenReturn(Collections.emptyList());

        when(usuarioRepository.save(any(Usuario.class)))
                .thenReturn(usuarioMock);

        when(psicologoMapper.toEntity(dto))
                .thenReturn(entityMock);

        when(psicologoRepository.save(any(Psicologo.class)))
                .thenReturn(entityMock);

        when(psicologoMapper.toDto(entityMock))
                .thenReturn(dto);

        // Act
        PsicologoDTO result = psicologoService.create(dto);

        // Assert
        assertNotNull(result);
        assertEquals("Ana", result.getNombre());

        verify(psicologoRepository).findByNumColegiatura(dto.getNumColegiatura());
        verify(usuarioRepository).save(any(Usuario.class));
        verify(psicologoMapper).toEntity(dto);
        verify(psicologoRepository).save(any(Psicologo.class));
        verify(psicologoMapper).toDto(entityMock);
    }*/

    @Test
    @DisplayName("CP02 - No se debe registrar psicólogo si ya existe colegiatura")
    void crearPsicologo_numColegiaturaExistente_lanzaExcepcion() {
        // Arrange
        when(psicologoRepository.findByNumColegiatura(dto.getNumColegiatura()))
                .thenReturn(Collections.singletonList(new Psicologo()));

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            psicologoService.create(dto);
        });

        assertEquals("Ya existe un psicologo con el mismo numero de colegiatura", exception.getMessage());

        // Verificamos que no se intente guardar el usuario ni el psicólogo
        verify(usuarioRepository, never()).save(any(Usuario.class));
        verify(psicologoRepository, never()).save(any(Psicologo.class));
    }


    @Test
    @DisplayName("CP03 - Actualizar psicólogo con datos válidos")
    void actualizarPsicologo_exitoso() {
        // Arrange
        Integer idPsicologo = 10;

        Psicologo psicologoExistente = new Psicologo();
        psicologoExistente.setIdPsicologo(idPsicologo);
        psicologoExistente.setNumColegiatura("9876543210");

        Psicologo psicologoActualizado = new Psicologo();
        psicologoActualizado.setIdPsicologo(idPsicologo);
        psicologoActualizado.setNombre(dto.getNombre());
        psicologoActualizado.setApellido(dto.getApellido());
        psicologoActualizado.setDni(dto.getDni());
        psicologoActualizado.setEdad(dto.getEdad());
        psicologoActualizado.setNumColegiatura(psicologoExistente.getNumColegiatura()); // no cambia
        psicologoActualizado.setDisponibilidad(dto.getDisponibilidad());
        psicologoActualizado.setDescripcionPsicologo(dto.getDescripcion());
        psicologoActualizado.setTarifa(dto.getTarifa());
        psicologoActualizado.setEspecialidad(dto.getEspecialidad());

        when(psicologoRepository.findById(idPsicologo)).thenReturn(java.util.Optional.of(psicologoExistente));
        when(psicologoRepository.findByNumColegiatura(psicologoExistente.getNumColegiatura()))
                .thenReturn(Collections.singletonList(psicologoExistente));
        when(psicologoRepository.save(any(Psicologo.class))).thenReturn(psicologoActualizado);
        when(psicologoMapper.toDto(psicologoActualizado)).thenReturn(dto);

        // Act
        PsicologoDTO resultado = psicologoService.update(idPsicologo, dto);

        // Assert
        assertNotNull(resultado);
        assertEquals("Ana", resultado.getNombre());
        assertEquals("Gomez", resultado.getApellido());
        assertEquals("12345678", resultado.getDni());

        verify(psicologoRepository).findById(idPsicologo);
        verify(psicologoRepository).save(psicologoExistente);
        verify(psicologoMapper).toDto(psicologoActualizado);
    }

    @Test
    @DisplayName("CP04 - No se debe actualizar si la colegiatura ya existe en otro psicólogo")
    void actualizarPsicologo_colegiaturaDuplicada_lanzaExcepcion() {
        // Arrange
        Integer idPsicologo = 10;
        Psicologo psicologoExistente = new Psicologo();
        psicologoExistente.setIdPsicologo(idPsicologo);
        psicologoExistente.setNumColegiatura("1234567890");

        Psicologo otroPsicologo = new Psicologo();
        otroPsicologo.setIdPsicologo(20);
        otroPsicologo.setNumColegiatura("1234567890");

        when(psicologoRepository.findById(idPsicologo)).thenReturn(java.util.Optional.of(psicologoExistente));
        when(psicologoRepository.findByNumColegiatura("1234567890")).thenReturn(Collections.singletonList(otroPsicologo));

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            psicologoService.update(idPsicologo, dto);
        });

        assertEquals("Ya existe un psicólogo con el mismo número de colegiatura", exception.getMessage());

        verify(psicologoRepository).findById(idPsicologo);
        verify(psicologoRepository).findByNumColegiatura("1234567890");
        verify(psicologoRepository, never()).save(any());
    }

    @Test
    @DisplayName("CP5 - No se debe actualizar si el ID no existe")
    void actualizarPsicologo_idInexistente_lanzaExcepcion() {
        // Arrange
        when(psicologoRepository.findById(99)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            psicologoService.update(99, dto);
        });

        assertEquals("El psicólogo con ID 99 no fue encontrado", exception.getMessage());
        verify(psicologoRepository).findById(99);
        verifyNoMoreInteractions(psicologoRepository);
    }

    @Test
    @DisplayName("CP6 - Eliminar psicólogo por ID existente")
    void eliminarPsicologo_existente() {
        // Arrange
        Integer id = 10;
        when(psicologoRepository.findById(id)).thenReturn(Optional.of(entityMock));

        // Act
        psicologoService.delete(id);

        // Assert
        verify(psicologoRepository).findById(id);
        verify(psicologoRepository).delete(entityMock);
    }

    @Test
    @DisplayName("CP7 - Eliminar psicólogo con ID inexistente lanza excepción")
    void eliminarPsicologo_inexistente_lanzaExcepcion() {
        // Arrange
        Integer id = 99;
        when(psicologoRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            psicologoService.delete(id);
        });

        assertEquals("El psicologo con ID "+id+" no fue encontrado", exception.getMessage());
        verify(psicologoRepository).findById(id);
        verify(psicologoRepository, never()).delete(any());
    }


    ///////// USER STORY 03-Búsqueda avanzada de psicólogos
    @Test
    @DisplayName("CP8 - Obtener todos los psicólogos")
    void obtenerTodosPsicologos_retornaLista() {
        // Arrange
        Psicologo psicologo1 = new Psicologo();
        psicologo1.setIdPsicologo(1);
        psicologo1.setNumColegiatura("111");

        Psicologo psicologo2 = new Psicologo();
        psicologo2.setIdPsicologo(2);
        psicologo2.setNumColegiatura("222");

        when(psicologoRepository.findAll())
                .thenReturn(List.of(psicologo1, psicologo2));

        PsicologoDTO dto1 = new PsicologoDTO();
        dto1.setNumColegiatura("111");

        PsicologoDTO dto2 = new PsicologoDTO();
        dto2.setNumColegiatura("222");

        when(psicologoMapper.toDto(psicologo1)).thenReturn(dto1);
        when(psicologoMapper.toDto(psicologo2)).thenReturn(dto2);

        // Act
        List<PsicologoDTO> resultado = psicologoService.findAll();

        // Assert
        assertEquals(2, resultado.size());
        assertEquals("111", resultado.get(0).getNumColegiatura());
        assertEquals("222", resultado.get(1).getNumColegiatura());

        verify(psicologoRepository).findAll();
        verify(psicologoMapper).toDto(psicologo1);
        verify(psicologoMapper).toDto(psicologo2);
    }

    ///////// USER STORY 04- Visualización de perfil del psicólogo
    @Test
    @DisplayName("CP9 - Buscar psicólogo por ID existente retorna DTO")
    void buscarPsicologoPorId_existente_retornaDTO() {
        // Arrange
        int id = 10;

        // Simular entidad encontrada
        when(psicologoRepository.findById(id))
                .thenReturn(Optional.of(entityMock));

        // Crear DTO de visualización simulado
        var visualizarDto = new com.ingsw.conectamente.dto.VisualizarPsicologoDTO();
        visualizarDto.setNombre("Ana");
        visualizarDto.setApellido("Gomez");
        visualizarDto.setEspecialidad(Especialidad.CLINICA);

        // Simular mapeo de entidad a DTO de visualización
        when(visualizacionPsicologoMapper.toDto(entityMock))
                .thenReturn(visualizarDto);

        // Act
        var resultado = psicologoService.findById(id);

        // Assert
        assertNotNull(resultado);
        assertEquals("Ana", resultado.getNombre());
        assertEquals("Gomez", resultado.getApellido());
        assertEquals(Especialidad.CLINICA, resultado.getEspecialidad());

        verify(psicologoRepository).findById(id);
        verify(visualizacionPsicologoMapper).toDto(entityMock);
    }

    @Test
    @DisplayName("CP10 - Buscar psicólogo por ID inexistente lanza excepción")
    void buscarPsicologoPorId_inexistente_lanzaExcepcion() {
        // Arrange
        int id = 999;
        when(psicologoRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            psicologoService.findById(id);
        });

        assertEquals("El psicologo con ID " + id + " no fue encontrado", exception.getMessage());

        verify(psicologoRepository).findById(id);
        verify(visualizacionPsicologoMapper, never()).toDto(any());
    }


    ///////// USER STORY 03 - Búsqueda avanzada de psicólogos
    @Test
    @DisplayName("CP11 - Filtrar psicólogo por especialidad")
    void testBuscarPorEspecialidad() {
        Especialidad especialidad = Especialidad.CLINICA;
        Psicologo p1 = new Psicologo();
        p1.setEspecialidad(especialidad);

        when(psicologoRepository.findByEspecialidad(especialidad)).thenReturn(List.of(p1));

        List<Psicologo> result = psicologoService.buscarPorFiltros(especialidad, null, null, null);

        assertEquals(1, result.size());
        assertEquals(especialidad, result.get(0).getEspecialidad());
        verify(psicologoRepository, times(1)).findByEspecialidad(especialidad);
    }

    @Test
    @DisplayName("CP12 - Filtrar psicólogo por disponibilidad")
    void testBuscarPorDisponibilidad() {
        String disponibilidad = "mañana";
        Psicologo p1 = new Psicologo();
        p1.setDisponibilidad(disponibilidad);

        when(psicologoRepository.findByDisponibilidadContaining(disponibilidad)).thenReturn(List.of(p1));

        List<Psicologo> result = psicologoService.buscarPorFiltros(null, disponibilidad, null, null);

        assertEquals(1, result.size());
        assertEquals(disponibilidad, result.get(0).getDisponibilidad());
        verify(psicologoRepository, times(1)).findByDisponibilidadContaining(disponibilidad);
    }

    @Test
    @DisplayName("CP13 - Filtrar psicólogo por tarifa")
    void testBuscarPorTarifa() {
        // Arrange
        Float min = 50.0f;
        Float max = 100.0f;

        Psicologo p1 = new Psicologo();
        p1.setTarifa(75.0f);

        Psicologo p2 = new Psicologo();
        p2.setTarifa(55.0f);

        when(psicologoRepository.findByTarifaBetween(min, max)).thenReturn(List.of(p1, p2));

        // Act
        List<Psicologo> result = psicologoService.buscarPorFiltros(null, null, min, max);

        // Assert
        assertEquals(2, result.size());
        for (Psicologo psicologo : result) {
            assertTrue(psicologo.getTarifa() >= min && psicologo.getTarifa() <= max);
        }
        verify(psicologoRepository, times(1)).findByTarifaBetween(min, max);
    }

    @Test
    @DisplayName("CP14 - Buscar psicólogo sin filtros debe retornar todos")
    void testBuscarSinFiltros() {
        Psicologo p1 = new Psicologo();
        Psicologo p2 = new Psicologo();

        when(psicologoRepository.findAll()).thenReturn(List.of(p1, p2));

        List<Psicologo> result = psicologoService.buscarPorFiltros(null, null, null, null);

        assertEquals(2, result.size());
        verify(psicologoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("CP15 - Filtrar por especialidad y tarifa debe retornar vacía si se ignoran combinaciones")
    void testBuscarPorEspecialidadYTarifa() {
        List<Psicologo> vacio = Collections.emptyList();

        when(psicologoRepository.findByEspecialidad(Especialidad.CLINICA)).thenReturn(vacio);

        List<Psicologo> result = psicologoService.buscarPorFiltros(Especialidad.CLINICA, null, 10f, 50f);

        // Solo se aplicará especialidad (según tu implementación actual), y no tarifa.
        assertTrue(result.isEmpty());
        verify(psicologoRepository).findByEspecialidad(Especialidad.CLINICA);
    }

    @Test
    @DisplayName("CP16 - Filtrar por especialidad y disponibilidad")
    void testFiltrarPorEspecialidadYDisponibilidad() {
        Especialidad especialidad = Especialidad.CLINICA;
        String disponibilidad = "mañana";

        Psicologo p = new Psicologo();
        p.setEspecialidad(especialidad);
        p.setDisponibilidad(disponibilidad);

        when(psicologoRepository.findByEspecialidad(especialidad)).thenReturn(List.of(p));
        when(psicologoRepository.findByDisponibilidadContaining(disponibilidad)).thenReturn(List.of(p));

        List<Psicologo> result = psicologoService.buscarPorFiltros(especialidad, disponibilidad, null, null);

        assertEquals(1, result.size());
        assertEquals(especialidad, result.get(0).getEspecialidad());
        assertEquals(disponibilidad, result.get(0).getDisponibilidad());
    }

    @Test
    @DisplayName("CP17 - Filtrar sin coincidencias retorna lista vacía")
    void testFiltrarSinCoincidencias() {
        Especialidad especialidad = Especialidad.ORGANIZACIONAL;
        String disponibilidad = "noche";

        when(psicologoRepository.findByEspecialidad(especialidad)).thenReturn(Collections.emptyList());
        when(psicologoRepository.findByDisponibilidadContaining(disponibilidad)).thenReturn(Collections.emptyList());

        List<Psicologo> result = psicologoService.buscarPorFiltros(especialidad, disponibilidad, null, null);

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("CP18 - Buscar psicólogos con filtro de tarifa que no devuelve resultados")
    void testBuscarPorTarifaSinResultados() {
        // Arrange
        Float min = 200.0f;
        Float max = 300.0f;

        when(psicologoRepository.findByTarifaBetween(min, max)).thenReturn(Collections.emptyList());

        // Act
        List<Psicologo> result = psicologoService.buscarPorFiltros(null, null, min, max);

        // Assert
        assertTrue(result.isEmpty());
        verify(psicologoRepository, times(1)).findByTarifaBetween(min, max);
    }
}
