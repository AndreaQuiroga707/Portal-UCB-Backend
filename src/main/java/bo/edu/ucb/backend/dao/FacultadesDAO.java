package bo.edu.ucb.backend.dao;

import bo.edu.ucb.backend.dto.FacultadesDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultadesDAO extends JpaRepository<FacultadesDTO, Integer> {
}