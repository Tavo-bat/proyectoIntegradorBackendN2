package co.edu.cesde.sgc_crud.controller;

import co.edu.cesde.sgc_crud.exception.CursoEncontradoException;
import co.edu.cesde.sgc_crud.exception.CursoInvalidoException;
import co.edu.cesde.sgc_crud.exception.CursoNoEncontradoException;
import co.edu.cesde.sgc_crud.models.Curso;
import co.edu.cesde.sgc_crud.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Curso> findAll() {
        return cursoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(cursoService.findById(id));
        } catch (CursoInvalidoException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (CursoNoEncontradoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Curso curso) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(curso));
        } catch (CursoInvalidoException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (CursoEncontradoException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Curso curso) {
        try {
            return ResponseEntity.ok(cursoService.update(curso));
        } catch (CursoInvalidoException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (CursoNoEncontradoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            cursoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (CursoInvalidoException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (CursoNoEncontradoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdate(@PathVariable Long id, @RequestBody Curso curso) {
        try {
            return ResponseEntity.ok(cursoService.patch(curso, id));
        } catch (CursoInvalidoException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (CursoNoEncontradoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}
