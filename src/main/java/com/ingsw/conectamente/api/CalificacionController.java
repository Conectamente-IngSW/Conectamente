package com.ingsw.conectamente.api;

import com.ingsw.conectamente.model.entity.Calificacion;
import com.ingsw.conectamente.model.entity.Psicologo;
import com.ingsw.conectamente.repository.CalificacionRepository;
import com.ingsw.conectamente.service.CalificacionService;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calificacion")
public class CalificacionController {
    private final CalificacionService calificacionService;

    @PostMapping
    public ResponseEntity<Calificacion> createCalificacion(@RequestBody Calificacion calificacion) {
        Calificacion createdCalificacion = calificacionService.createCalificacion(calificacion);
        return new ResponseEntity<Calificacion>(createdCalificacion, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Calificacion> getCalificacionById(@PathVariable("id") Integer id){
        Calificacion calificacion = calificacionService.findCalificacionById(id);
        return new ResponseEntity<Calificacion>(calificacion, HttpStatus.OK);
    }

    @GetMapping("/psicologo/{idPsicologo}")
    public ResponseEntity<List<Calificacion>> getCalificacionesByPsicologo(@PathVariable Integer idPsicologo) {
        List<Calificacion> calificaciones = calificacionService.findCalificacionesByPsicologoId(idPsicologo);
        if (calificaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(calificaciones);
    }

}
