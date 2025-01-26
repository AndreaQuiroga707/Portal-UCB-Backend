package bo.edu.ucb.backend.dao;

import bo.edu.ucb.backend.dto.CursoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoDAO extends JpaRepository<CursoDTO, Integer> {
}