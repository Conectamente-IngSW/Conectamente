package com.ingsw.conectamente.api;

import com.ingsw.conectamente.model.entity.Psicologo;
import com.ingsw.conectamente.service.PsicologoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/psicologo")
public class PsicologoControlador {

    private final PsicologoService psicologoService;

    @PostMapping
    public ResponseEntity<Psicologo> createPsicologo(@RequestBody Psicologo psicologo) {
        Psicologo createdPsicologo = psicologoService.create(psicologo);
        return new ResponseEntity<Psicologo>(createdPsicologo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Psicologo> updatePsicologo(@PathVariable("id") Integer id, @RequestBody Psicologo psicologo) {
        Psicologo updatePsicologo = psicologoService.update(id, psicologo);
        return new ResponseEntity<Psicologo>(updatePsicologo, HttpStatus.OK);
    }
}
