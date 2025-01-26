package bo.edu.ucb.backend.dao;

import bo.edu.ucb.backend.dto.CentrosInvestigacionDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CentrosInvestigacionDAO extends JpaRepository<CentrosInvestigacionDTO, Integer> {
}
