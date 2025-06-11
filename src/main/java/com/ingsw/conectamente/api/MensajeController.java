package com.ingsw.conectamente.api;

import com.ingsw.conectamente.model.entity.Mensaje;
import com.ingsw.conectamente.service.MensajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mensaje")
public class MensajeController {
    private final MensajeService mensajeService;

    @PostMapping
    public ResponseEntity<Mensaje> createMensaje(@RequestBody Mensaje mensaje) {
        Mensaje created = mensajeService.createMensaje(mensaje);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Mensaje>> getMensajesByUsuarioId(
            @PathVariable("idUsuario") Integer idUsuario) {
        List<Mensaje> mensajes = mensajeService.findMensajeByUsuarioId(idUsuario);
        if (mensajes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mensajes);
    }

    @DeleteMapping("/usuario/{idUsuario}")
    public ResponseEntity<Void> deleteMensajesByUsuarioId(
            @PathVariable("idUsuario") Integer idUsuario) {
        mensajeService.deleteMensajeByUsuarioId(idUsuario);
        return ResponseEntity.noContent().build();
    }
}
