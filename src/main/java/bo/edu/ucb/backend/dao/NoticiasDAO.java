package bo.edu.ucb.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bo.edu.ucb.backend.entity.Noticias;

@Repository
public interface NoticiasDAO extends JpaRepository<Noticias, Integer>{
    
}
