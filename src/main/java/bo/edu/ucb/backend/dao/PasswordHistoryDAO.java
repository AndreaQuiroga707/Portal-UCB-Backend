package bo.edu.ucb.backend.dao;

import bo.edu.ucb.backend.entity.PasswordHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PasswordHistoryDAO extends JpaRepository<PasswordHistory, Integer> {

    @Query(value = "SELECT ph.hashed_password " +
            "FROM password_history ph " +
            "WHERE ph.user_id = :userId " +
            "ORDER BY ph.created_at DESC " +
            "LIMIT 6", nativeQuery = true)
    List<String> findLast6PasswordsByUserId(@Param("userId") Integer userId);
}
