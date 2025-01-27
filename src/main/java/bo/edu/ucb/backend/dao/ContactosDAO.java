package bo.edu.ucb.backend.dao;

import bo.edu.ucb.backend.entity.Contactos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactosDAO extends JpaRepository<Contactos, Integer> {
}
