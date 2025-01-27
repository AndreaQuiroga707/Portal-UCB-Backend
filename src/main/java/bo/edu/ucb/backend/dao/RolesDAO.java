package bo.edu.ucb.backend.dao;

import bo.edu.ucb.backend.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesDAO extends JpaRepository<Roles, Integer> {
    Roles findByNombre(String nombre);
}
