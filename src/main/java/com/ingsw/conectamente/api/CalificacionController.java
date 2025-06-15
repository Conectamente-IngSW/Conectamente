package com.ingsw.conectamente.api;

import com.ingsw.conectamente.dto.CalificacionDTO;
import com.ingsw.conectamente.service.CalificacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calificaciones")
@PreAuthorize("hasAnyRole('ADMINISTRADOR', 'PACIENTE')") // Restriction at class level
public class CalificacionController {
    private final CalificacionService calificacionService;

    @PostMapping
    public ResponseEntity<CalificacionDTO> create(@RequestBody @Valid CalificacionDTO calificacionDTO) {
        CalificacionDTO createdCalificacion = calificacionService.createCalificacion(calificacionDTO);
        return new ResponseEntity<>(createdCalificacion, HttpStatus.CREATED);
    }

    @GetMapping("/psicologo/{idPsicologo}")
    public ResponseEntity<List<CalificacionDTO>> getByPsicologoId(@PathVariable Integer idPsicologo) {
        List<CalificacionDTO> calificaciones = calificacionService.findCalificacionesByPsicologoId(idPsicologo);
        if (calificaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(calificaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalificacionDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(calificacionService.findCalificacionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalificacionDTO> update(@PathVariable Integer id, @Valid @RequestBody CalificacionDTO calificacionDTO) {
        CalificacionDTO updatedCalificacion = calificacionService.update(id, calificacionDTO);
        return new ResponseEntity<>(updatedCalificacion, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        calificacionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
