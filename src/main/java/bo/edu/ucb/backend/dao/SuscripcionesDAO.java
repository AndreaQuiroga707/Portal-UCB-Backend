package bo.edu.ucb.backend.dao;

import bo.edu.ucb.backend.dto.SuscripcionesDTO;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SuscripcionesDAO extends JpaRepository<SuscripcionesDTO, Integer> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM suscripciones WHERE correo = :correoToFind", nativeQuery = true)
    void deleteByCorreo(@Param("correoToFind") String correoToFind);
}
