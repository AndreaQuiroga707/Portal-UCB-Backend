package bo.edu.ucb.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.edu.ucb.backend.entity.Eventos;

public interface EventosDAO extends JpaRepository<Eventos, Integer>{
    
}
