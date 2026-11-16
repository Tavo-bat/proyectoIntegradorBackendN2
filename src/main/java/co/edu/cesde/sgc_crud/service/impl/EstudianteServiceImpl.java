package co.edu.cesde.sgc_crud.service.impl;

import co.edu.cesde.sgc_crud.exception.EstudianteEncontradoException;
import co.edu.cesde.sgc_crud.exception.EstudianteInvalidoException;
import co.edu.cesde.sgc_crud.exception.EstudianteNoEncontradoException;
import co.edu.cesde.sgc_crud.models.Estudiante;
import co.edu.cesde.sgc_crud.repository.EstudianteRepository;
import co.edu.cesde.sgc_crud.service.EstudianteService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteServiceImpl(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    public List<Estudiante> findAll() {
        return estudianteRepository.findAll();
    }

    @Override
    public Estudiante findById(Long id) {
        if (id == null || id <= 0) {
            throw new EstudianteInvalidoException("Debe ingresar un ID válido para realizar la consulta");
        }
        Estudiante estudiante = estudianteRepository.findById(id).orElse(null);
        if (estudiante == null) {
            throw new EstudianteNoEncontradoException("El estudiante con ID " + id + " no existe");
        }
        return estudiante;
    }

    @Override
    @Transactional
    public Estudiante save(Estudiante estudiante) {
        validarDatosObligatorios(estudiante);

        if (estudiante.getId() != null) {
            Estudiante estudianteEncontrado = estudianteRepository.findById(estudiante.getId()).orElse(null);

            if (estudianteEncontrado != null) {
                throw new EstudianteEncontradoException("El estudiante con ID " + estudiante.getId() + " ya existe");
            }
        }

        return estudianteRepository.save(estudiante);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (id == null || id <= 0) {
            throw new EstudianteInvalidoException("El ID del estudiante es obligatorio y debe ser válido.");
        }

        Estudiante estudianteNoEncontrado = estudianteRepository.findById(id).orElse(null);

        if (estudianteNoEncontrado == null) {
            throw new EstudianteNoEncontradoException("El estudiante con ID " + id + " no existe");
        }
        estudianteRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Estudiante update(Estudiante estudiante) {
        if (estudiante == null || estudiante.getId() == null || estudiante.getId() <= 0) {
            throw new EstudianteInvalidoException("El ID del estudiante es obligatorio para actualizar");
        }

        validarDatosObligatorios(estudiante);

        Estudiante estudianteExistente = estudianteRepository.findById(estudiante.getId()).orElse(null);

        if (estudianteExistente == null) {
            throw new EstudianteNoEncontradoException("El estudiante con ID " + estudiante.getId() + " no existe");
        }

        estudianteExistente.setNombre(estudiante.getNombre());
        estudianteExistente.setCorreo(estudiante.getCorreo());
        estudianteExistente.setTelefono(estudiante.getTelefono());
        estudianteExistente.setActivo(estudiante.getActivo());

        return estudianteRepository.save(estudianteExistente);
    }

    @Override
    @Transactional
    public Estudiante patch(Estudiante estudiante, Long id) {
        if (id == null || id <= 0) {
            throw new EstudianteInvalidoException("El ID del estudiante es obligatorio y debe ser válido");
        }
        if (estudiante == null) {
            throw new EstudianteInvalidoException("Los datos del estudiante son obligatorios");
        }

        Estudiante updateEstudiante = estudianteRepository.findById(id).orElse(null);

        if (updateEstudiante == null) {
            throw new EstudianteNoEncontradoException("El estudiante con ID " + id + " no existe");
        }

        if (estudiante.getNombre() != null && !estudiante.getNombre().isBlank()) {
            updateEstudiante.setNombre(estudiante.getNombre());
        }
        if (estudiante.getCorreo() != null && !estudiante.getCorreo().isBlank()) {
            updateEstudiante.setCorreo(estudiante.getCorreo());
        }
        if (estudiante.getTelefono() != null && !estudiante.getTelefono().isBlank()) {
            updateEstudiante.setTelefono(estudiante.getTelefono());
        }
        if (estudiante.getActivo() != null) {
            updateEstudiante.setActivo(estudiante.getActivo());
        }

        return estudianteRepository.save(updateEstudiante);
    }

    private void validarDatosObligatorios(Estudiante estudiante) {
        if (estudiante == null) {
            throw new EstudianteInvalidoException("Los datos del estudiante son obligatorios");
        }
        if (estudiante.getNombre() == null || estudiante.getNombre().isBlank()) {
            throw new EstudianteInvalidoException("El nombre del estudiante es obligatorio");
        }
        if (estudiante.getCorreo() == null || estudiante.getCorreo().isBlank()) {
            throw new EstudianteInvalidoException("El correo del estudiante es obligatorio");
        }
        if (estudiante.getTelefono() == null || estudiante.getTelefono().isBlank()) {
            throw new EstudianteInvalidoException("El teléfono del estudiante es obligatorio");
        }
        if (estudiante.getActivo() == null) {
            throw new EstudianteInvalidoException("El estado del estudiante es obligatorio");
        }
    }
}
