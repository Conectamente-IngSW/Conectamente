package com.ingsw.conectamente.mapper;

import com.ingsw.conectamente.dto.ConversacionDTO;
import com.ingsw.conectamente.dto.CrearConversacionDTO;
import com.ingsw.conectamente.model.entity.Conversacion;
import com.ingsw.conectamente.model.entity.Paciente;
import com.ingsw.conectamente.model.entity.Psicologo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ConversacionMapper {

    private final MensajeMapper mensajeMapper;

    public ConversacionDTO toDto(Conversacion conversacion) {
        ConversacionDTO dto = new ConversacionDTO();
        dto.setIdConversacion(conversacion.getIdConversacion());
        dto.setPacienteId(conversacion.getPaciente().getIdPaciente());
        dto.setPsicologoId(conversacion.getPsicologo().getIdPsicologo());
        dto.setFechaCreacion(conversacion.getFechaCreacion());

        if (conversacion.getMensajes() != null) {
            dto.setMensajes(conversacion.getMensajes().stream()
                    .map(mensajeMapper::toDto)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public Conversacion toEntity(CrearConversacionDTO dto) {
        Conversacion conversacion = new Conversacion();
        Paciente paciente = new Paciente();
        paciente.setIdPaciente(dto.getPacienteId());
        conversacion.setPaciente(paciente);

        Psicologo psicologo = new Psicologo();
        psicologo.setIdPsicologo(dto.getPsicologoId());
        conversacion.setPsicologo(psicologo);

        return conversacion;
    }
}
