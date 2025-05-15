package com.ingsw.conectamente.api;

import com.ingsw.conectamente.dto.PerfilPsicologoDTO;
import com.ingsw.conectamente.service.PsicologoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/psicologos")
public class PsicologoController {

    private final PsicologoService psicologoService;

    public PsicologoController(PsicologoService psicologoService) {
        this.psicologoService = psicologoService;
    }

    @GetMapping("/{id}/perfil")
    public PerfilPsicologoDTO obtenerPerfil(@PathVariable int id) {
        return psicologoService.obtenerPerfilPsicologo(id);
    }
}
