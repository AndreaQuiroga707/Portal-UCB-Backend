package bo.edu.ucb.backend.dao;

import bo.edu.ucb.backend.dto.InstitutosInvestigacionDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutoInvestigacionDAO extends JpaRepository<InstitutosInvestigacionDTO, Integer> {
}
