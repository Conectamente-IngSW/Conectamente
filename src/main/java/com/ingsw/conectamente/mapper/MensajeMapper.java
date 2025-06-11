package com.ingsw.conectamente.mapper;

import com.ingsw.conectamente.dto.MensajeDTO;
import com.ingsw.conectamente.model.entity.Mensaje;
import com.ingsw.conectamente.model.entity.Paciente;
import com.ingsw.conectamente.model.entity.Psicologo;
import com.ingsw.conectamente.model.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MensajeMapper {
    private final ModelMapper modelMapper;

    public Mensaje toDto(Mensaje mensaje) {
        // Mapea los campos simples
        MensajeDTO dto = modelMapper.map(mensaje, MensajeDTO.class);

        // Aplana las relaciones
        if (mensaje.getUsuario() != null) {
            dto.setUsuarioId(mensaje.getUsuario().getIdUsuario());
        }
        if (mensaje.getPsicologo() != null) {
            dto.setPsicologoId(mensaje.getPsicologo().getIdPsicologo());
        }
        if (mensaje.getPaciente() != null) {
            dto.setPacienteId(mensaje.getPaciente().getIdPaciente());
        }

        return dto;
    }

    /**
     * Convierte un DTO MensajeDTO a la entidad Mensaje,
     * creando objetos “vacíos” para las relaciones y seteando sólo el ID.
     */
    public Mensaje toEntity(MensajeDTO dto) {
        // Mapea los campos simples
        Mensaje mensaje = modelMapper.map(dto, Mensaje.class);

        // Reconstruye las relaciones con sólo el ID
        if (dto.getUsuarioId() != null) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(dto.getUsuarioId());
            mensaje.setUsuario(usuario);
        }
        if (dto.getPsicologoId() != null) {
            Psicologo psicologo = new Psicologo();
            psicologo.setIdPsicologo(dto.getPsicologoId());
            mensaje.setPsicologo(psicologo);
        }
        if (dto.getPacienteId() != null) {
            Paciente paciente = new Paciente();
            paciente.setIdPaciente(dto.getPacienteId());
            mensaje.setPaciente(paciente);
        }

        return mensaje;
    }
}
