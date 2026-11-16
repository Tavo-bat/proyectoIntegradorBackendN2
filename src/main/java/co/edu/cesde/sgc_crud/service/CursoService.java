package co.edu.cesde.sgc_crud.service;

import co.edu.cesde.sgc_crud.models.Curso;

import java.util.List;


public interface CursoService {

    List<Curso> findAll();
    Curso findById(Long id);
    Curso save(Curso curso);
    void deleteById(Long id);
    Curso update(Curso curso);
    Curso patch(Curso curso, Long id);

}
