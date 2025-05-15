package com.ingsw.conectamente.service;

import com.ingsw.conectamente.repository.PsicologoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PsicologoService {

    private final PsicologoRepository PsicologoRepository;

    public PerfilPsicologoDTO obtenerPerfil(int idPsicologo) {
        Psicologo psicologo = psicologoRepository.findById(idPsicologo)
                .orElseThrow(() -> new RuntimeException("Psicólogo no encontrado"));

        // Construir DTO
        PerfilPsicologoDTO dto = new PerfilPsicologoDTO();
        dto.setNombreCompleto(psicologo.getUsuario().getNombre() + " " + psicologo.getUsuario().getApellido());
        dto.setEmail(psicologo.getUsuario().getEmail());
        dto.setDescripcion(psicologo.getDescripcion());
        dto.setEspecialidad(psicologo.getEspecialidad().name()); // Si es enum
        dto.setTarifa(psicologo.getTarifa());
        dto.setDisponibilidad(psicologo.getDisponibilidad());

        // Mapear calificaciones
        List<CalificacionDTO> calificacionesDTO = psicologo.getCalificaciones().stream().map(calificacion -> {
            CalificacionDTO calDTO = new CalificacionDTO();
            calDTO.setPuntaje(calificacion.getPuntaje());
            calDTO.setComentario(calificacion.getComentario());
            calDTO.setFecha(calificacion.getFecha());
            calDTO.setNombrePaciente(calificacion.getPaciente().getUsuario().getNombre()); // Suponiendo que tienes esa relación
            return calDTO;
        }).collect(Collectors.toList());

        dto.setCalificaciones(calificacionesDTO);

        return dto;
    }
}

