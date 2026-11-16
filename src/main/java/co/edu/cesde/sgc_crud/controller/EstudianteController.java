package co.edu.cesde.sgc_crud.controller;

import co.edu.cesde.sgc_crud.exception.EstudianteInvalidoException;
import co.edu.cesde.sgc_crud.exception.EstudianteEncontradoException;
import co.edu.cesde.sgc_crud.exception.EstudianteNoEncontradoException;
import co.edu.cesde.sgc_crud.models.Estudiante;
import co.edu.cesde.sgc_crud.service.EstudianteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    public List<Estudiante> findAll() {
        return estudianteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(estudianteService.findById(id));
        } catch (EstudianteInvalidoException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (EstudianteNoEncontradoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Estudiante estudiante) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(estudianteService.save(estudiante));
        } catch (EstudianteInvalidoException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (EstudianteEncontradoException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Estudiante estudiante) {
        try {
            return ResponseEntity.ok(estudianteService.update(estudiante));
        } catch (EstudianteInvalidoException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (EstudianteNoEncontradoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdate(@PathVariable Long id, @RequestBody Estudiante estudiante) {
        try {
            return ResponseEntity.ok(estudianteService.patch(estudiante, id));
        } catch (EstudianteInvalidoException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (EstudianteNoEncontradoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            estudianteService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EstudianteInvalidoException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (EstudianteNoEncontradoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}
