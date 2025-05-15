package com.ingsw.conectamente.api;

import com.ingsw.conectamente.model.entity.Paciente;
import com.ingsw.conectamente.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> createPaciente(@RequestBody Paciente paciente) {
        Paciente createdPaciente = pacienteService.create(paciente);
        return new ResponseEntity<Paciente>(createdPaciente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> updatePaciente(@PathVariable("id") Integer id, @RequestBody Paciente paciente) {
        Paciente updatePaciente = pacienteService.update(id, paciente);
        return new ResponseEntity<Paciente>(updatePaciente, HttpStatus.OK);
    }
}
