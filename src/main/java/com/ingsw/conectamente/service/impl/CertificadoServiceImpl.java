package com.ingsw.conectamente.service.impl;

import com.ingsw.conectamente.model.entity.Certificado;
import com.ingsw.conectamente.repository.CertificadoRepository;
import com.ingsw.conectamente.service.CertificadoService;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificadoServiceImpl implements CertificadoService {
    private static final long MAX_FILE_SIZE = 2 * 1024 * 1024; // 2 MB
    private final CertificadoRepository certificadoRepository;
    private final Tika tika = new Tika();

    @Override
    public Certificado createCertificado(Certificado certificado) {
        //Validar tamaño
        if (certificado.getFileData().length > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("El archivo excede el tamaño máximo permitido de 2 MB");
        }
        //Validar tipo de archivo
        try (InputStream is = new ByteArrayInputStream(certificado.getFileData())) {
            String tipoDetectado = tika.detect(is);  // ésta sí declara throws IOException
            if (!"application/pdf".equalsIgnoreCase(tipoDetectado)) {
                throw new IllegalArgumentException("Solo se permiten archivos en formato PDF");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al validar el tipo de archivo", e);
        }


        // 3. Persistir si pasó todas las validaciones
        return certificadoRepository.save(certificado);
    }

    @Override
    public List<Certificado> findCertificadoByPsicologoId(Integer idPsicologo) {
        return certificadoRepository.findByPsicologoIdPsicologo(idPsicologo);
    }
}
