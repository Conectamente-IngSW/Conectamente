package com.ingsw.conectamente.repository;

import com.ingsw.conectamente.model.entity.Mensaje;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
    @Query("SELECT m FROM Mensaje m WHERE " +
            "(m. = :userId1 AND m.receiverId = :userId2) OR " +
            "(m.senderId = :userId2 AND m.receiverId = :userId1) " +
            "ORDER BY m.timestamp ASC")
    List<Mensaje> findConversation(@Param("userId1") Integer userId1, @Param("userId2") Integer userId2);
}
