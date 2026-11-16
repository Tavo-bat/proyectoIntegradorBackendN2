package co.edu.cesde.sgc_crud.service.impl;

import co.edu.cesde.sgc_crud.exception.CursoEncontradoException;
import co.edu.cesde.sgc_crud.exception.CursoInvalidoException;
import co.edu.cesde.sgc_crud.exception.CursoNoEncontradoException;
import co.edu.cesde.sgc_crud.models.Curso;
import co.edu.cesde.sgc_crud.repository.CursoRepository;
import co.edu.cesde.sgc_crud.service.CursoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    @Override
    public Curso findById(Long id) {
        if (id == null || id <= 0) {
            throw new CursoInvalidoException("Debe ingresar un ID válido para realizar la consulta.");
        }
        Curso curso = cursoRepository.findById(id).orElse(null);
        if (curso == null) {
            throw new CursoNoEncontradoException("El curso con ID " + id + " no existe.");
        }
        return curso;
    }

    @Override
    @Transactional
    public Curso save(Curso curso) {
        validarDatosObligatorios(curso);

        if (curso.getId() != null) {
            Curso cursoEncontrado = cursoRepository.findById(curso.getId()).orElse(null);
            if (cursoEncontrado != null) {
                throw new CursoEncontradoException("El curso con ID " + curso.getId() + " ya existe.");
            }
        }

        return cursoRepository.save(curso);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (id == null || id <= 0) {
            throw new CursoInvalidoException("El ID del curso es obligatorio y debe ser válido.");
        }

        Curso cursoNoEncontrado = cursoRepository.findById(id).orElse(null);

        if (cursoNoEncontrado == null) {
            throw new CursoNoEncontradoException("El curso con ID " + id + " no existe.");
        }

        cursoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Curso update(Curso curso) {
        if (curso == null || curso.getId() == null || curso.getId() <= 0) {
            throw new CursoInvalidoException("El ID del curso es obligatorio para actualizar.");
        }

        validarDatosObligatorios(curso);

        Curso cursoExistente = cursoRepository.findById(curso.getId()).orElse(null);

        if (cursoExistente == null) {
            throw new CursoNoEncontradoException("El curso con ID " + curso.getId() + " no existe.");
        }

        cursoExistente.setNombre(curso.getNombre());
        cursoExistente.setDescripcion(curso.getDescripcion());
        cursoExistente.setActivo(curso.getActivo());

        return cursoRepository.save(cursoExistente);
    }

    @Override
    @Transactional
    public Curso patch(Curso curso, Long id) {
        if (id == null || id <= 0) {
            throw new CursoInvalidoException("El ID del curso es obligatorio y debe ser válido.");
        }
        if (curso == null) {
            throw new CursoInvalidoException("Los datos del curso son obligatorios.");
        }

        Curso updateCurso = cursoRepository.findById(id).orElse(null);

        if (updateCurso == null) {
            throw new CursoNoEncontradoException("El curso con ID " + id + " no existe.");
        }

        if (curso.getNombre() != null && !curso.getNombre().isBlank()) {
            updateCurso.setNombre(curso.getNombre());
        }
        if (curso.getDescripcion() != null && !curso.getDescripcion().isBlank()) {
            updateCurso.setDescripcion(curso.getDescripcion());
        }
        if (curso.getActivo() != null) {
            updateCurso.setActivo(curso.getActivo());
        }

        return cursoRepository.save(updateCurso);
    }

    private void validarDatosObligatorios(Curso curso) {
        if (curso == null) {
            throw new CursoInvalidoException("Los datos del curso son obligatorios.");
        }
        if (curso.getNombre() == null || curso.getNombre().isBlank()) {
            throw new CursoInvalidoException("El nombre del curso es obligatorio.");
        }
        if (curso.getDescripcion() == null || curso.getDescripcion().isBlank()) {
            throw new CursoInvalidoException("La descripción del curso es obligatoria.");
        }
        if (curso.getActivo() == null) {
            throw new CursoInvalidoException("El estado del curso es obligatorio.");
        }
    }

}
