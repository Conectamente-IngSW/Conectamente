package com.ingsw.conectamente.service;

import com.ingsw.conectamente.model.entity.Certificado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CertificadoService {
    Certificado createCertificado(Certificado certificado);
    List<Certificado> findCertificadoByPsicologoId(Integer id);

}
