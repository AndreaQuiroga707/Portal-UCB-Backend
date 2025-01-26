package bo.edu.ucb.backend.dao;

import bo.edu.ucb.backend.dto.ContactosDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactosDAO extends JpaRepository<ContactosDTO, Integer> {
}
