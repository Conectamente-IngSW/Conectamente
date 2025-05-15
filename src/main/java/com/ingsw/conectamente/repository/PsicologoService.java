package com.ingsw.conectamente.repository;

import com.ingsw.conectamente.model.entity.Psicologo;
import org.springframework.stereotype.Repository;

@Repository
public interface PsicologoRepository extends JpaRepository<Psicologo, Integer> {
    Optional<Psicologo> findById(int id);
}

