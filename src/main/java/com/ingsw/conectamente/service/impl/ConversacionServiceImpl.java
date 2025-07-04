package com.ingsw.conectamente.service.impl;

import com.ingsw.conectamente.exception.ResourceNotFoundException;
import com.ingsw.conectamente.model.entity.Conversacion;
import com.ingsw.conectamente.model.entity.Paciente;
import com.ingsw.conectamente.model.entity.Psicologo;
import com.ingsw.conectamente.repository.ConversacionRepository;
import com.ingsw.conectamente.repository.PacienteRepository;
import com.ingsw.conectamente.repository.PsicologoRepository;
import com.ingsw.conectamente.service.ConversacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversacionServiceImpl implements ConversacionService {

    private final ConversacionRepository conversacionRepository;
    private final PacienteRepository pacienteRepository;
    private final PsicologoRepository psicologoRepository;

    @Override
    @Transactional
    public Conversacion crearConversacion(Conversacion conversacion) {
        // 1) Fija siempre el id a null para forzar INSERT
        conversacion.setIdConversacion(null);
        // 2) Genera la fecha de creación aquí
        conversacion.setFechaCreacion(LocalDateTime.now());

        // Luego valida que existan paciente y psicólogo
        Integer pacienteId = conversacion.getPaciente().getIdPaciente();
        Integer psicologoId = conversacion.getPsicologo().getIdPsicologo();

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente", "idPaciente", pacienteId));
        Psicologo psicologo = psicologoRepository.findById(psicologoId)
                .orElseThrow(() -> new ResourceNotFoundException("Psicologo", "idPsicologo", psicologoId));

        conversacion.setPaciente(paciente);
        conversacion.setPsicologo(psicologo);

        // Y al guardarlo, Hibernate sabrá que debe hacer un INSERT
        return conversacionRepository.save(conversacion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Conversacion> findConversacionByPacienteId(Integer pacienteId) {
        return conversacionRepository.findByPaciente_IdPaciente(pacienteId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Conversacion> findConversacionByPsicologoId(Integer psicologoId) {
        return conversacionRepository.findByPsicologo_IdPsicologo(psicologoId);
    }

    @Override
    @Transactional(readOnly = true)
    public Conversacion findConversacionById(Integer idConversacion) {
        return conversacionRepository.findById(idConversacion)
                .orElseThrow(() -> new ResourceNotFoundException("Conversacion", "idConversacion", idConversacion));
    }
}
