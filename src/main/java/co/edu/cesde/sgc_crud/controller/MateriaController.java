package co.edu.cesde.sgc_crud.controller;

import co.edu.cesde.sgc_crud.exception.MateriaEncontradaException;
import co.edu.cesde.sgc_crud.exception.MateriaInvalidaException;
import co.edu.cesde.sgc_crud.exception.MateriaNoEncontradaException;
import co.edu.cesde.sgc_crud.models.Materia;
import co.edu.cesde.sgc_crud.service.MateriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    private final MateriaService materiaService;

    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @GetMapping
    public List<Materia> findAll() {
        return materiaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(materiaService.findById(id));
        } catch (MateriaInvalidaException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (MateriaNoEncontradaException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Materia materia) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(materiaService.save(materia));
        } catch (MateriaInvalidaException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (MateriaEncontradaException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Materia materia) {
        try {
            return ResponseEntity.ok(materiaService.update(materia));
        } catch (MateriaInvalidaException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (MateriaNoEncontradaException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            materiaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (MateriaInvalidaException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (MateriaNoEncontradaException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdate(@PathVariable Long id, @RequestBody Materia materia) {
        try {
            return ResponseEntity.ok(materiaService.patch(materia, id));
        } catch (MateriaInvalidaException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (MateriaNoEncontradaException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}
