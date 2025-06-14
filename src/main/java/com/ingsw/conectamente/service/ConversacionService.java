
package com.ingsw.conectamente.service;

import com.ingsw.conectamente.model.entity.Conversacion;

import java.util.List;

public interface ConversacionService {

    Conversacion crearConversacion(Conversacion conversacion);

    List<Conversacion> findConversacionByPacienteId(Integer pacienteId);

    List<Conversacion> findConversacionByPsicologoId(Integer psicologoId);

    Conversacion findConversacionById(Integer idConversacion);
}
