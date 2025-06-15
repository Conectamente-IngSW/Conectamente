package com.ingsw.conectamente.repository;

import com.ingsw.conectamente.model.entity.Conversacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConversacionRepository extends JpaRepository<Conversacion, Integer> {
    List<Conversacion> findByPaciente_IdPaciente(Integer pacienteId);
    List<Conversacion> findByPsicologo_IdPsicologo(Integer psicologoId);
}
