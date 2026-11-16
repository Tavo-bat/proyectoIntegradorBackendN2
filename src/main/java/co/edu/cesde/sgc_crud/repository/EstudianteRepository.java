package co.edu.cesde.sgc_crud.repository;

import co.edu.cesde.sgc_crud.models.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
}
