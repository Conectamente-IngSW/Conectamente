package com.ingsw.conectamente.service;

import com.ingsw.conectamente.model.entity.Certificado;

import java.util.List;

public interface CertificadoService {
    Certificado createCertificado(Certificado certificado);
    List<Certificado> findCertificadoByPsicologoId(Integer id);

}
