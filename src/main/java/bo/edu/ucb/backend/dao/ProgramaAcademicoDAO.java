package bo.edu.ucb.backend.dao;

import bo.edu.ucb.backend.dto.ProgramaAcademicoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramaAcademicoDAO extends JpaRepository<ProgramaAcademicoDTO, Integer> {
    // Puedes agregar consultas personalizadas si es necesario
}
