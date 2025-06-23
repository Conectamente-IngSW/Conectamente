package com.ingsw.conectamente.api;

import com.ingsw.conectamente.dto.ConversacionDTO;
import com.ingsw.conectamente.dto.CrearConversacionDTO;
import com.ingsw.conectamente.mapper.ConversacionMapper;
import com.ingsw.conectamente.model.entity.Conversacion;
import com.ingsw.conectamente.service.ConversacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/conversaciones")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasAnyRole('ADMINISTRADOR', 'PSICOLOGO', 'PACIENTE')")
public class ConversacionController {

    private final ConversacionService conversacionService;
    private final ConversacionMapper conversacionMapper;

    @PostMapping
    public ResponseEntity<ConversacionDTO> crearConversacion(
            @Valid @RequestBody CrearConversacionDTO dto) {
        Conversacion entidad = conversacionMapper.toEntity(dto);
        Conversacion creada = conversacionService.crearConversacion(entidad);
        ConversacionDTO respuesta = conversacionMapper.toDto(creada);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConversacionDTO> obtenerPorId(
            @PathVariable("id") Integer id) {
        Conversacion entidad = conversacionService.findConversacionById(id);
        return ResponseEntity.ok(conversacionMapper.toDto(entidad));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<ConversacionDTO>> listarPorPaciente(
            @PathVariable Integer pacienteId) {
        List<Conversacion> lista = conversacionService.findConversacionByPacienteId(pacienteId);
        List<ConversacionDTO> dtos = lista.stream()
                .map(conversacionMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/psicologo/{psicologoId}")
    public ResponseEntity<List<ConversacionDTO>> listarPorPsicologo(
            @PathVariable Integer psicologoId) {
        List<Conversacion> lista = conversacionService.findConversacionByPsicologoId(psicologoId);
        List<ConversacionDTO> dtos = lista.stream()
                .map(conversacionMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
