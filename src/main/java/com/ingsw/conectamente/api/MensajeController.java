package com.ingsw.conectamente.api;

import com.ingsw.conectamente.model.entity.Mensaje;
import com.ingsw.conectamente.model.entity.Paciente;
import com.ingsw.conectamente.service.MensajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mensaje")
public class MensajeController {
    private final MensajeService mensajeService;

    @PostMapping
    public ResponseEntity<Mensaje> createMensaje(@RequestBody Mensaje mensaje) {
        Mensaje createdMensaje = mensajeService.createMensaje(mensaje);
        return new ResponseEntity<Mensaje>(createdMensaje, HttpStatus.CREATED);
    }

}
