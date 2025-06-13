package com.ingsw.conectamente.service;

import com.ingsw.conectamente.dto.PsicologoDTO;
import com.ingsw.conectamente.dto.VisualizarPsicologoDTO;
import com.ingsw.conectamente.enums.Especialidad;
import com.ingsw.conectamente.model.entity.Psicologo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PsicologoService {
    List<PsicologoDTO> findAll();
    List<VisualizarPsicologoDTO> getAll();
    Page<PsicologoDTO> getAll(Pageable pageable);
    PsicologoDTO create(PsicologoDTO psicologoDTO);
    Page<PsicologoDTO> paginate(Pageable pageable);
    VisualizarPsicologoDTO findById(Integer id);
    PsicologoDTO update(Integer id, PsicologoDTO updatePsicologoDTO);
    List<Psicologo> buscarPorFiltros(Especialidad especialidad, String disponibilidad, Float minTarifa, Float maxTarifa);
    void delete(Integer id);
}
