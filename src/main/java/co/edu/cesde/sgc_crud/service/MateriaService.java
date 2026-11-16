package co.edu.cesde.sgc_crud.service;

import co.edu.cesde.sgc_crud.models.Materia;

import java.util.List;

public interface MateriaService {

    List<Materia> findAll();
    Materia findById(Long id);
    Materia save(Materia materia);
    void deleteById(Long id);
    Materia update(Materia materia);
    Materia patch(Materia materia, Long id);

}
