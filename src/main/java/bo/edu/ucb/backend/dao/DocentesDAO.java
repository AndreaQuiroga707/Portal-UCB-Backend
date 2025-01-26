package bo.edu.ucb.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.edu.ucb.backend.dto.DocentesDTO;

public interface DocentesDAO extends JpaRepository<DocentesDTO, Integer>{
    
}
