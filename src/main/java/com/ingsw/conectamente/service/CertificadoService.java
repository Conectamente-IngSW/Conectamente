package com.ingsw.conectamente.service;

import com.ingsw.conectamente.model.entity.Certificado;

import java.util.List;

public interface CertificadoService {
Certificado create(Certificado certificado);
Certificado findById(Long id);
List<Certificado> findAll();
Certificado update(Long idCertificado, Certificado newCertificado);
}
