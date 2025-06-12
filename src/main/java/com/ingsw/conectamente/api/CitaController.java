package com.ingsw.conectamente.api;

import com.ingsw.conectamente.dto.CitaDTO;
import com.ingsw.conectamente.service.CitaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cita")
public class CitaController {
    private final CitaService citaService;

    @PostMapping
    public ResponseEntity<CitaDTO> create(@RequestBody @Valid CitaDTO citaDTO) {
        CitaDTO createdCita = citaService.createCita(citaDTO);
        return new ResponseEntity<>(createdCita, HttpStatus.CREATED);
    }

    @GetMapping("/psicologo/{idPsicologo}")
    public ResponseEntity<List<CitaDTO>> getPsicologoByCitaId(@PathVariable Integer idPsicologo) {
        List<CitaDTO> citas = citaService.findCitaByPsicologoId(idPsicologo);
        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<CitaDTO>> getPacienteByCitaId(@PathVariable Integer idPaciente) {
        List<CitaDTO> citas = citaService.findCitaByPacienteId(idPaciente);
        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(citaService.findCitaById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<CitaDTO> update(@PathVariable Integer id, @Valid @RequestBody CitaDTO citaDTO) {
        CitaDTO updatedCalificacion = citaService.update(id, citaDTO);
        return new ResponseEntity<>(updatedCalificacion, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        citaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
