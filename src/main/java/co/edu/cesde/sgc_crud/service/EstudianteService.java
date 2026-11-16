package co.edu.cesde.sgc_crud.service;

import co.edu.cesde.sgc_crud.models.Estudiante;

import java.util.List;

public interface EstudianteService {

    List<Estudiante> findAll();
    Estudiante findById(Long id);
    Estudiante save(Estudiante estudiante);
    void deleteById(Long id);
    Estudiante update(Estudiante estudiante);
    Estudiante patch(Estudiante estudiante, Long id);

}
