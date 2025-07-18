package com.ingsw.conectamente.api;

import com.ingsw.conectamente.dto.PsicologoDTO;
import com.ingsw.conectamente.dto.VisualizarPsicologoDTO;
import com.ingsw.conectamente.enums.Departamento;
import com.ingsw.conectamente.enums.Especialidad;
import com.ingsw.conectamente.model.entity.Psicologo;
import com.ingsw.conectamente.service.PsicologoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/psicologo")
@PreAuthorize("hasAnyRole('ADMINISTRADOR','PSICOLOGO')") //Aplicar la restriccion a nivel de clase
public class PsicologoController {

    private final PsicologoService psicologoService;


    @GetMapping
    public ResponseEntity<List<VisualizarPsicologoDTO>> listAll() {
        List<VisualizarPsicologoDTO> psicologos = psicologoService.getAll();
        return new ResponseEntity<>(psicologos, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<PsicologoDTO>> paginate(@PageableDefault(size = 10, sort = "numColegiatura") Pageable pageable) {
        Page<PsicologoDTO> page = psicologoService.paginate(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PsicologoDTO> create(@Valid @RequestBody PsicologoDTO psicologoDTO) {
        PsicologoDTO createdPsicologo = psicologoService.create(psicologoDTO);
        return new ResponseEntity<>(createdPsicologo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PsicologoDTO> update(@PathVariable Integer id,@Valid @RequestBody PsicologoDTO psicologoDTO) {
        PsicologoDTO updatedPsicologo = psicologoService.update(id, psicologoDTO);
        return new ResponseEntity<>(updatedPsicologo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        psicologoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/buscar")
    public List<VisualizarPsicologoDTO> buscarPsicologos(
            @RequestParam(required = false) Especialidad especialidad,
            @RequestParam(required = false) Departamento departamento,
            @RequestParam(required = false) Float minTarifa,
            @RequestParam(required = false) Float maxTarifa,
            @RequestParam(required = false) String nombre
    ) {
        return psicologoService.buscarPorFiltros(especialidad, departamento, minTarifa, maxTarifa, nombre);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisualizarPsicologoDTO> getPerfil(@PathVariable Integer id) {
        VisualizarPsicologoDTO dto = psicologoService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
