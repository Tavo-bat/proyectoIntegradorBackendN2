package co.edu.cesde.sgc_crud.repository;

import co.edu.cesde.sgc_crud.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
