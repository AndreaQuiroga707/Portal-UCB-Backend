package bo.edu.ucb.backend.dao;

import bo.edu.ucb.backend.dto.CarrerasDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarrerasDAO extends JpaRepository<CarrerasDTO, Integer> {
    List<CarrerasDTO> findByFacultadFacultadId(Integer facultadId);
    // Agrega este nuevo método
    CarrerasDTO findByNombre(String nombre);
}