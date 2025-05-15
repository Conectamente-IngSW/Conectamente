package com.ingsw.conectamente.service.impl;

import com.ingsw.conectamente.model.entity.Certificado;
import com.ingsw.conectamente.model.entity.Psicologo;
import com.ingsw.conectamente.repository.CertificadoRepository;
import com.ingsw.conectamente.service.CertificadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificadoServiceImpl implements CertificadoService {

    private final CertificadoRepository certificadoRepository;

    @Transactional
    @Override
    public Certificado create(Certificado certificado){ return Certificado.save(certificado);
    }

    @Override
    public Certificado findById(Long id) {
        return Certificado.findById(id).orElseThrow(()->new RuntimeException("Certificado no enecontrado"));
        }

    @Transactional
    @Override
    public Certificado update(Long id, Certificado updatedCertificado){
        Certificado certificado = findById(id);
        certificadodeDB.setNombreArchivo(updatedCertificado. getCertificado);
    }

    @Override
    public List<Certificado> findAll() {
        return certificadoRepository.findAll();
    }


}
