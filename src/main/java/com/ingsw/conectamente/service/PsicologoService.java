package com.ingsw.conectamente.service;

import com.ingsw.conectamente.dto.CalificacionDTO;
import com.ingsw.conectamente.dto.PerfilPsicologoDTO;
import com.ingsw.conectamente.model.entity.Calificacion;
import com.ingsw.conectamente.model.entity.Psicologo;
import com.ingsw.conectamente.repository.PsicologoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PsicologoService {

    private final PsicologoRepository psicologoRepository;

    public PsicologoService(PsicologoRepository psicologoRepository) {
        this.psicologoRepository = psicologoRepository;
    }

    public PerfilPsicologoDTO obtenerPerfilPsicologo(int id) {
        Psicologo psicologo = psicologoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Psicólogo no encontrado"));

        PerfilPsicologoDTO dto = new PerfilPsicologoDTO();
        dto.setNombre(psicologo.getUsuario().getNombre());
        dto.setApellido(psicologo.getUsuario().getApellido());
        dto.setEspecialidad(psicologo.getEspecialidad().name()); // si usas Enum
        dto.setDescripcion(psicologo.getDescripcion());
        dto.setTarifa(psicologo.getTarifa());
        dto.setDisponibilidad(psicologo.getDisponibilidad());
        dto.setDireccion(psicologo.getUsuario().getDireccion().getNombreDireccion());

        List<CalificacionDTO> calificaciones = psicologo.getCalificaciones()
                .stream()
                .map(this::mapCalificacionToDTO)
                .collect(Collectors.toList());

        dto.setCalificaciones(calificaciones);
        return dto;
    }

    private CalificacionDTO mapCalificacionToDTO(Calificacion calificacion) {
        CalificacionDTO dto = new CalificacionDTO();
        dto.setPuntaje(calificacion.getPuntaje());
        dto.setComentario(calificacion.getComentario());
        dto.setFecha(calificacion.getFecha());
        dto.setNombrePaciente(calificacion.getPaciente().getUsuario().getNombre());
        return dto;
    }
}
