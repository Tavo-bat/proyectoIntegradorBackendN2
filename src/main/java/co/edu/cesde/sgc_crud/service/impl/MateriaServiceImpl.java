package co.edu.cesde.sgc_crud.service.impl;

import co.edu.cesde.sgc_crud.exception.MateriaEncontradaException;
import co.edu.cesde.sgc_crud.exception.MateriaInvalidaException;
import co.edu.cesde.sgc_crud.exception.MateriaNoEncontradaException;
import co.edu.cesde.sgc_crud.models.Materia;
import co.edu.cesde.sgc_crud.repository.MateriaRepository;
import co.edu.cesde.sgc_crud.service.MateriaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaServiceImpl implements MateriaService {

    private final MateriaRepository materiaRepository;

    public MateriaServiceImpl(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    @Override
    public List<Materia> findAll() {
        return materiaRepository.findAll();
    }

    @Override
    public Materia findById(Long id) {
        if (id == null || id <= 0) {
            throw new MateriaInvalidaException("Debe ingresar un ID válido para realizar la consulta.");
        }
        Materia materia = materiaRepository.findById(id).orElse(null);
        if (materia == null) {
            throw new MateriaNoEncontradaException("La materia con ID " + id + " no existe.");
        }
        return materia;
    }

    @Override
    @Transactional
    public Materia save(Materia materia) {
        validarDatosObligatorios(materia);

        if (materia.getId() != null) {
            Materia materiaEncontrada = materiaRepository.findById(materia.getId()).orElse(null);
            if (materiaEncontrada != null) {
                throw new MateriaEncontradaException("La materia con ID " + materia.getId() + " ya existe.");
            }
        }

        return materiaRepository.save(materia);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (id == null || id <= 0) {
            throw new MateriaInvalidaException("El ID de la materia es obligatorio y debe ser válido.");
        }

        Materia materiaNoEncontrada = materiaRepository.findById(id).orElse(null);

        if (materiaNoEncontrada == null) {
            throw new MateriaNoEncontradaException("La materia con ID " + id + " no existe.");
        }

        materiaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Materia update(Materia materia) {
        if (materia == null || materia.getId() == null || materia.getId() <= 0) {
            throw new MateriaInvalidaException("El ID de la materia es obligatorio para actualizar.");
        }

        validarDatosObligatorios(materia);

        Materia materiaExistente = materiaRepository.findById(materia.getId()).orElse(null);

        if (materiaExistente == null) {
            throw new MateriaNoEncontradaException("La materia con ID " + materia.getId() + " no existe.");
        }

        materiaExistente.setNombre(materia.getNombre());
        materiaExistente.setDescripcion(materia.getDescripcion());
        materiaExistente.setCreditos(materia.getCreditos());
        materiaExistente.setActivo(materia.getActivo());

        return materiaRepository.save(materiaExistente);
    }

    @Override
    @Transactional
    public Materia patch(Materia materia, Long id) {
        if (id == null || id <= 0) {
            throw new MateriaInvalidaException("El ID de la materia es obligatorio y debe ser válido.");
        }
        if (materia == null) {
            throw new MateriaInvalidaException("Los datos de la materia son obligatorios.");
        }

        Materia updateMateria = materiaRepository.findById(id).orElse(null);

        if (updateMateria == null) {
            throw new MateriaNoEncontradaException("La materia con ID " + id + " no existe.");
        }

        if (materia.getNombre() != null && !materia.getNombre().isBlank()) {
            updateMateria.setNombre(materia.getNombre());
        }
        if (materia.getDescripcion() != null && !materia.getDescripcion().isBlank()) {
            updateMateria.setDescripcion(materia.getDescripcion());
        }
        if (materia.getCreditos() != null) {
            if (materia.getCreditos() < 0) {
                throw new MateriaInvalidaException("Los créditos de la materia no pueden ser negativos.");
            }
            updateMateria.setCreditos(materia.getCreditos());
        }
        if (materia.getActivo() != null) {
            updateMateria.setActivo(materia.getActivo());
        }

        return materiaRepository.save(updateMateria);
    }

    private void validarDatosObligatorios(Materia materia) {
        if (materia == null) {
            throw new MateriaInvalidaException("Los datos de la materia son obligatorios.");
        }
        if (materia.getNombre() == null || materia.getNombre().isBlank()) {
            throw new MateriaInvalidaException("El nombre de la materia es obligatorio.");
        }
        if (materia.getCreditos() != null && materia.getCreditos() < 0) {
            throw new MateriaInvalidaException("Los créditos de la materia no pueden ser negativos.");
        }
        if (materia.getActivo() == null) {
            throw new MateriaInvalidaException("El estado de la materia es obligatorio.");
        }
    }
}
