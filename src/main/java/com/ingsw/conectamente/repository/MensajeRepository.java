package com.ingsw.conectamente.repository;

import com.ingsw.conectamente.model.entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
    List<Mensaje> findByUsuario_IdUsuario(Integer idUsuario);
    @Modifying
    @Transactional
    void deleteByUsuario_IdUsuario(Integer idUsuario);
}
