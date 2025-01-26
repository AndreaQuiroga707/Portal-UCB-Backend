package bo.edu.ucb.backend.dao;

import bo.edu.ucb.backend.dto.GruposInvestigacionDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GruposInvestigacionDAO extends JpaRepository<GruposInvestigacionDTO, Integer> {
}
