package bo.edu.ucb.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.edu.ucb.backend.dto.DocentesDeCarreraDTO;

public interface DocentesDeCarreraDAO extends JpaRepository<DocentesDeCarreraDTO, Integer>{
    
}
