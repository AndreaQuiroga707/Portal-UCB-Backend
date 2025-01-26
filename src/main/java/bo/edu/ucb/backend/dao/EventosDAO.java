package bo.edu.ucb.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.edu.ucb.backend.dto.EventosDTO;

public interface EventosDAO extends JpaRepository<EventosDTO, Integer>{
    
}
