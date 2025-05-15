package com.ingsw.conectamente.service;

import com.ingsw.conectamente.model.entity.Mensaje;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MensajeService {
    Mensaje createMensaje(Mensaje mensaje);
    List<Mensaje> findMensajeById(Integer id);

}
