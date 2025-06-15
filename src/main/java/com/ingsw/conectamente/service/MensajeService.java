package com.ingsw.conectamente.service;

import com.ingsw.conectamente.model.entity.Mensaje;

import java.util.List;

public interface MensajeService {
    Mensaje createMensaje(Mensaje mensaje);
    List<Mensaje> findMensajeByUsuarioId(Integer idUsuario);
    void deleteMensajeByUsuarioId(Integer idUsuario);

}
