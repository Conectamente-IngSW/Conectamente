package com.ingsw.conectamente.service;

import com.ingsw.conectamente.exception.ResourceNotFoundException;
import com.ingsw.conectamente.model.entity.Conversacion;
import com.ingsw.conectamente.model.entity.Paciente;
import com.ingsw.conectamente.model.entity.Psicologo;
import com.ingsw.conectamente.repository.ConversacionRepository;
import com.ingsw.conectamente.repository.PacienteRepository;
import com.ingsw.conectamente.repository.PsicologoRepository;
import com.ingsw.conectamente.service.impl.ConversacionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class ConversacionServiceUnitTest {

    @Mock
    private ConversacionRepository conversacionRepository;

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private PsicologoRepository psicologoRepository;

    @InjectMocks
    private ConversacionServiceImpl conversacionService;

    private Paciente pacienteMock;
    private Psicologo psicologoMock;
    private Conversacion conversacionMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // 1) Creamos los mocks de Paciente y Psicólogo y stub de findById
        pacienteMock = new Paciente();
        pacienteMock.setIdPaciente(1);
        when(pacienteRepository.findById(anyInt()))
                .thenReturn(Optional.of(pacienteMock));

        psicologoMock = new Psicologo();
        psicologoMock.setIdPsicologo(2);
        when(psicologoRepository.findById(anyInt()))
                .thenReturn(Optional.of(psicologoMock));

        // 2) Preparamos la Conversacion que devolverá el save()
        conversacionMock = new Conversacion();
        conversacionMock.setIdConversacion(42);
        conversacionMock.setPaciente(pacienteMock);
        conversacionMock.setPsicologo(psicologoMock);
        conversacionMock.setFechaCreacion(LocalDateTime.now());
        when(conversacionRepository.save(any(Conversacion.class)))
                .thenReturn(conversacionMock);
    }

//    @Test
//    @DisplayName("CP01 - Crear conversación con entidad válida")
//    void crearConversacion_exitoso() {
//        // Act
//        Conversacion result = conversacionService.crearConversacion(conversacionMock);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(42, result.getIdConversacion());
//        // Verificamos que buscó paciente y psicólogo antes de guardar
//        verify(pacienteRepository).findById(1);
//        verify(psicologoRepository).findById(2);
//        // Y que al final llamó al save()
//        verify(conversacionRepository).save(any(Conversacion.class));
//    }

    @Test
    @DisplayName("CP02 - Buscar conversaciones por paciente existente")
    void findConversacionByPacienteId_retornaLista() {
        Integer idPac = 1;
        when(conversacionRepository.findByPaciente_IdPaciente(idPac))
                .thenReturn(List.of(conversacionMock));

        List<Conversacion> lista = conversacionService.findConversacionByPacienteId(idPac);

        assertEquals(1, lista.size());
        assertSame(conversacionMock, lista.get(0));
        verify(conversacionRepository).findByPaciente_IdPaciente(idPac);
    }

    @Test
    @DisplayName("CP03 - Buscar conversaciones por psicólogo existente")
    void findConversacionByPsicologoId_retornaLista() {
        Integer idPsi = 2;
        when(conversacionRepository.findByPsicologo_IdPsicologo(idPsi))
                .thenReturn(List.of(conversacionMock));

        List<Conversacion> lista = conversacionService.findConversacionByPsicologoId(idPsi);

        assertEquals(1, lista.size());
        assertSame(conversacionMock, lista.get(0));
        verify(conversacionRepository).findByPsicologo_IdPsicologo(idPsi);
    }

    @Test
    @DisplayName("CP04 - Buscar conversación por ID existente")
    void findConversacionById_existente_retornaEntidad() {
        Integer id = 42;
        when(conversacionRepository.findById(id))
                .thenReturn(Optional.of(conversacionMock));

        Conversacion encontrada = conversacionService.findConversacionById(id);

        assertNotNull(encontrada);
        assertEquals(42, encontrada.getIdConversacion());
        verify(conversacionRepository).findById(id);
    }

    @Test
    @DisplayName("CP05 - Buscar conversación por ID inexistente lanza excepción")
    void findConversacionById_inexistente_lanzaExcepcion() {
        Integer id = 99;
        when(conversacionRepository.findById(id))
                .thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(
                ResourceNotFoundException.class,
                () -> conversacionService.findConversacionById(id)
        );

        // Observa que éste es exactamente el mensaje que lanza tu ServiceImpl
        assertEquals(
                "Conversacion no encontrado con idConversacion : '99'",
                ex.getMessage()
        );
        verify(conversacionRepository).findById(id);
    }
}
