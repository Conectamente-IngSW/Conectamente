package com.ingsw.conectamente.repository;

import com.ingsw.conectamente.model.entity.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Integer> {
    List<Calificacion> findByPsicologo_IdPsicologo(int idPsicologo);
}
