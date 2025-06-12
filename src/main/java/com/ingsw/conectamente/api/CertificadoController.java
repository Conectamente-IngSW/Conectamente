package com.ingsw.conectamente.api;


import com.ingsw.conectamente.model.entity.Certificado;
import com.ingsw.conectamente.repository.CertificadoRepository;
import com.ingsw.conectamente.service.CertificadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/certificado")
@PreAuthorize("hasRole('PSICOLOGO')")
public class CertificadoController {
    private final CertificadoService certificadoService;
    private final CertificadoRepository certificadoRepository;

    @PostMapping
    public ResponseEntity<Certificado> createCertificado(@RequestBody Certificado certificado) {
        Certificado createdCertificado = certificadoService.createCertificado(certificado);
        return new ResponseEntity<Certificado>(createdCertificado, HttpStatus.CREATED);
    }

    @GetMapping("/psicologo/{idPsicologo}")
    public ResponseEntity<List<Certificado>> getCertificadosByPsicologo(@PathVariable Integer idPsicologo) {
        List<Certificado> certificados = certificadoService.findCertificadoByPsicologoId(idPsicologo);
        if (certificados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(certificados);
    }
}
