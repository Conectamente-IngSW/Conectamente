package com.ingsw.conectamente.service.impl;


import com.ingsw.conectamente.model.entity.Psicologo;
import com.ingsw.conectamente.model.entity.Usuario;
import com.ingsw.conectamente.repository.PsicologoRepository;
import com.ingsw.conectamente.service.PsicologoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PsicologoServiceImpl implements PsicologoService {
    private final PsicologoRepository psicologoRepository;

    @Transactional
    @Override
    public Psicologo create(Psicologo psicologo) {
        return psicologoRepository.save(psicologo);
    }

    @Override
    public Psicologo findById(Integer id) {
        return psicologoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Psicologo no encontrado"));
    }

    @Transactional
    @Override
    public Psicologo update(Integer id, Psicologo updatedPsicologo) {
        Psicologo psicologoFromDb = findById(id);
        psicologoFromDb.setDisponibilidad(updatedPsicologo.getDisponibilidad());
        psicologoFromDb.setDescripcion(updatedPsicologo.getDescripcion());
        psicologoFromDb.setTarifa(updatedPsicologo.getTarifa());

        Usuario usuarioFromDb = psicologoFromDb.getUsuario_idUsuario();
        Usuario updatedUsuario = updatedPsicologo.getUsuario_idUsuario();

        if (updatedUsuario.getNombre() != null) {
            usuarioFromDb.setNombre(updatedUsuario.getNombre());
        }
        if (updatedUsuario.getApellido() != null) {
            usuarioFromDb.setApellido(updatedUsuario.getApellido());
        }
        if (updatedUsuario.getEmail() != null) {
            usuarioFromDb.setEmail(updatedUsuario.getEmail());
        }
        if (updatedUsuario.getEdad() != null) {
            usuarioFromDb.setEdad(updatedUsuario.getEdad());
        }
        if (updatedUsuario.getDni() != null) {
            usuarioFromDb.setDni(updatedUsuario.getDni());
        }
        if (updatedUsuario.getContrasenia() != null) {
            usuarioFromDb.setContrasenia(updatedUsuario.getContrasenia());
        }

        return psicologoRepository.save(psicologoFromDb);
    }
}
