package com.ingsw.conectamente.service;

import com.ingsw.conectamente.model.entity.Psicologo;

import java.util.List;

public interface PsicologoService {
    Psicologo create(Psicologo psicologo);
    Psicologo findById(Integer id);
    Psicologo update(Integer id, Psicologo updatedPsicologo);
    List<Psicologo> findAll();
}
