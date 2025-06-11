package com.ingsw.conectamente.api;

import com.ingsw.conectamente.model.entity.Mensaje;
import com.ingsw.conectamente.repository.MensajeRepository;
import com.ingsw.conectamente.service.MensajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mensaje")
@PreAuthorize("hasAnyRole('ADMINISTRADOR', 'PSICOLOGO', 'PACIENTE')") // Restriction at class level
public class MensajeController {
    private final MensajeService mensajeService;
    private final MensajeRepository mensajeRepository;

    @PostMapping
    public ResponseEntity<Mensaje> createMensaje(@RequestBody Mensaje mensaje) {
        Mensaje createdMensaje = mensajeService.createMensaje(mensaje);
        return new ResponseEntity<Mensaje>(createdMensaje, HttpStatus.CREATED);
    }

    @GetMapping("/psicologo/{idPsicologo}")
    public ResponseEntity<List<Mensaje>> getCalificacionesByPsicologo(@PathVariable Integer idPsicologo) {
        List<Mensaje> mensajes = mensajeService.findMensajeByPsicologoId(idPsicologo);
        if (mensajes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mensajes);
    }

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<Mensaje>> getCalificacionesByPaciente(@PathVariable Integer idPaciente) {
        List<Mensaje> mensajes = mensajeService.findMensajeByPsicologoId(idPaciente);
        if (mensajes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mensajes);
    }



}
