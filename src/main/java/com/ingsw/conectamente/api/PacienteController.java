package com.ingsw.conectamente.api;

import com.ingsw.conectamente.dto.PacienteDTO;
import com.ingsw.conectamente.dto.VisualizarPacienteDTO;
import com.ingsw.conectamente.model.entity.Usuario;
import com.ingsw.conectamente.service.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/paciente")
@PreAuthorize("hasAnyRole('ADMINISTRADOR','PACIENTE')") // Restriction at class level
public class PacienteController {
    private final PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<VisualizarPacienteDTO>> listAll() {
        List<VisualizarPacienteDTO> psicologos = pacienteService.getAll();
        return new ResponseEntity<>(psicologos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisualizarPacienteDTO> getPerfil(@PathVariable Integer id) {
        VisualizarPacienteDTO dto = pacienteService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @PostMapping
    public ResponseEntity<PacienteDTO> create(@Valid @RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO createdPsicologo = pacienteService.create(pacienteDTO);
        return new ResponseEntity<>(createdPsicologo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> update(@PathVariable Integer id,@Valid @RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO updatedPaciente = pacienteService.update(id, pacienteDTO);
        return new ResponseEntity<>(updatedPaciente, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        pacienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Buscar Paciende por id de usuario
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<VisualizarPacienteDTO> getPacientePorUsuario(@PathVariable Integer idUsuario) {
        VisualizarPacienteDTO paciente = pacienteService.findByUsuarioId(idUsuario);
        return ResponseEntity.ok(paciente);
    }
}
