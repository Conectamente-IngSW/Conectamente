package com.ingsw.conectamente.service.impl;

import com.ingsw.conectamente.model.entity.Mensaje;
import com.ingsw.conectamente.repository.MensajeRepository;
import com.ingsw.conectamente.service.MensajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MensajeServiceImpl implements MensajeService {
    private final MensajeRepository mensajeRepository;

    @Override
    public Mensaje createMensaje(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);
    }

    @Override
    public List<Mensaje> findMensajeByPsicologoId(Integer idPsicologo) {
        return mensajeRepository.findByPsicologoIdPsicologo(idPsicologo);
    }

    @Override
    public List<Mensaje> findMensajeByPacienteId(Integer idPaciente) {
        return mensajeRepository.findByPsicologoIdPsicologo(idPaciente);
    }


}
