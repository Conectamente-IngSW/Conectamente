package com.ingsw.conectamente.service.impl;

import com.ingsw.conectamente.model.entity.Calificacion;
import com.ingsw.conectamente.repository.CalificacionRepository;
import com.ingsw.conectamente.repository.PsicologoRepository;
import com.ingsw.conectamente.service.CalificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalificacionServiceImpl implements CalificacionService {
    private final CalificacionRepository calificacionRepository;
    private final PsicologoRepository psicologoRepository;

    @Override
    public Calificacion createCalificacion(Calificacion calificacion) {
        return calificacionRepository.save(calificacion);
    }

    @Override
    public Calificacion findCalificacionById(Integer id) {
        return calificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el calificacion con el id: " + id));
    }

}
