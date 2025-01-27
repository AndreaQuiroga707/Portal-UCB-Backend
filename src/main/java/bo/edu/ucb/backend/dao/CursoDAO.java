package bo.edu.ucb.backend.dao;

import bo.edu.ucb.backend.entity.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoDAO extends JpaRepository<Cursos, Integer> {
}