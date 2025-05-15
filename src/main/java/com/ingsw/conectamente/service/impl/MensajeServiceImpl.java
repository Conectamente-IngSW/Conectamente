package com.ingsw.conectamente.service.impl;

import com.ingsw.conectamente.model.entity.Mensaje;
import com.ingsw.conectamente.repository.MensajeRepository;
import com.ingsw.conectamente.service.MensajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MensajeServiceImpl implements MensajeService {
    private final MensajeRepository mensajeRepository;

    @Override
    public Mensaje createMensaje(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);

    }
}
