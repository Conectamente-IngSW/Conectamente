package com.ingsw.conectamente.repository;

import com.ingsw.conectamente.model.entity.Certificado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificadoRepository extends JpaRepository<Certificado, Integer> {
    List<Certificado> findByPsicologoIdPsicologo(Integer idPsicologo);
}
