package com.ingsw.conectamente.service.impl;


import com.ingsw.conectamente.dto.PsicologoDTO;
import com.ingsw.conectamente.dto.VisualizarPsicologoDTO;
import com.ingsw.conectamente.enums.Especialidad;
import com.ingsw.conectamente.enums.Rol;
import com.ingsw.conectamente.exception.BadRequestException;
import com.ingsw.conectamente.exception.ResourceNotFoundException;
import com.ingsw.conectamente.mapper.PsicologoMapper;
import com.ingsw.conectamente.mapper.VisualizacionPsicologoMapper;
import com.ingsw.conectamente.model.entity.Psicologo;
import com.ingsw.conectamente.model.entity.Usuario;
import com.ingsw.conectamente.repository.PsicologoRepository;
import com.ingsw.conectamente.repository.UsuarioRepository;
import com.ingsw.conectamente.service.PsicologoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PsicologoServiceImpl implements PsicologoService {
    private final PsicologoRepository psicologoRepository;
    private final PsicologoMapper psicologoMapper;
    private final VisualizacionPsicologoMapper visualizacionPsicologoMapper;
    private final UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    @Override
    public List<VisualizarPsicologoDTO>getAll() {
        List<Psicologo> psicologos = psicologoRepository.findAll();
        return psicologos.stream().map(visualizacionPsicologoMapper::toDto).toList();
    }

    @Override
    public Page<PsicologoDTO> getAll(Pageable pageable) {
        Page<Psicologo> psicologos = psicologoRepository.findAll(pageable);
        return psicologos.map(psicologoMapper::toDto);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<PsicologoDTO> paginate(Pageable pageable) {
        Page<Psicologo> psicologos = psicologoRepository.findAll(pageable);
        return psicologos.map(psicologoMapper::toDto);
    }

    @Transactional
    @Override
    public PsicologoDTO create(PsicologoDTO psicologoDTO) {
        List<Psicologo> existentes = psicologoRepository.findByNumColegiatura(psicologoDTO.getNumColegiatura());
        if (!existentes.isEmpty()) {
            throw new BadRequestException("Ya existe un psicologo con el mismo numero de colegiatura");
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(psicologoDTO.getEmail());
        usuario.setContrasenia(psicologoDTO.getContrasenia());
        usuario.setRol(Rol.PSICOLOGO);
        usuario = usuarioRepository.save(usuario);

        Psicologo psicologo = psicologoMapper.toEntity(psicologoDTO);
        psicologo.setCreatedAt(LocalDateTime.now());
        psicologo = psicologoRepository.save(psicologo);
        return psicologoMapper.toDto(psicologo);
    }

    @Override
    public VisualizarPsicologoDTO findById(Integer id) {
        Psicologo psicologo = psicologoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El psicologo con ID "+id+" no fue encontrado"));
        return visualizacionPsicologoMapper.toDto(psicologo);
    }

    @Transactional
    @Override
    public PsicologoDTO update(Integer id, PsicologoDTO updatePsicologoDTO) {
        Psicologo psicologoFromDb = psicologoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El psicólogo con ID " + id + " no fue encontrado"));

        // Validar número de colegiatura duplicado
        List<Psicologo> existentes = psicologoRepository.findByNumColegiatura(updatePsicologoDTO.getNumColegiatura());
        boolean existeOtro = existentes.stream()
                .anyMatch(existingPsicologo -> !existingPsicologo.getIdPsicologo().equals(id));
        if (existeOtro) {
            throw new BadRequestException("Ya existe un psicólogo con el mismo número de colegiatura");
        }

        // Actualizar campos básicos
        psicologoFromDb.setNombrePsicologo(updatePsicologoDTO.getNombre());
        psicologoFromDb.setApellidoPsicologo(updatePsicologoDTO.getApellido());
        psicologoFromDb.setEdadPsicologo(updatePsicologoDTO.getEdad());
        psicologoFromDb.setDisponibilidad(updatePsicologoDTO.getDisponibilidad());
        psicologoFromDb.setDescripcionPsicologo(updatePsicologoDTO.getDescripcion());
        psicologoFromDb.setTarifa(updatePsicologoDTO.getTarifa());
        psicologoFromDb.setEspecialidad(updatePsicologoDTO.getEspecialidad());
        psicologoFromDb.setNumColegiatura(updatePsicologoDTO.getNumColegiatura());
        psicologoFromDb.setUpdatedAt(LocalDateTime.now());

        // Guardar cambios
        psicologoFromDb = psicologoRepository.save(psicologoFromDb);
        return psicologoMapper.toDto(psicologoFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Psicologo psicologo = psicologoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El psicologo con ID " + id + " no fue encontrado"));
        psicologoRepository.delete(psicologo);
    }

    public List<Psicologo> buscarPorFiltros(Especialidad especialidad, String disponibilidad, Float minTarifa, Float maxTarifa) {
        // Ejemplo simple: puedes combinar los filtros según la lógica que desees
        if (especialidad != null) {
            return psicologoRepository.findByEspecialidad(especialidad);
        }
        if (disponibilidad != null) {
            return psicologoRepository.findByDisponibilidadContaining(disponibilidad);
        }
        if (minTarifa != null && maxTarifa != null) {
            return psicologoRepository.findByTarifaBetween(minTarifa, maxTarifa);
        }
        return psicologoRepository.findAll();
    }

    @Override
    public List<PsicologoDTO> findAll() {
        List<Psicologo> psicologos = psicologoRepository.findAll();
        return psicologos.stream().map(psicologoMapper::toDto).toList();
    }

}