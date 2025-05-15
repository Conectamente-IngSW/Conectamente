package com.ingsw.conectamente.repository;

import com.ingsw.conectamente.model.entity.Psicologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PsicologoRepository extends JpaRepository<Psicologo, Integer> {
    // Puedes agregar consultas personalizadas si lo necesitas, por ejemplo:
    // List<Psicologo> findByEspecialidad(Especialidad especialidad);
}