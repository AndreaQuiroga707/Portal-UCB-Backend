package bo.edu.ucb.backend.dao;

import bo.edu.ucb.backend.entity.Carreras;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarrerasDAO extends JpaRepository<Carreras, Integer> {
    List<Carreras> findByFacultadFacultadId(Integer facultadId);
    // Agrega este nuevo método
    Carreras findByNombre(String nombre);
}