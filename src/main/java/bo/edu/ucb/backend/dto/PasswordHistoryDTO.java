package bo.edu.ucb.backend.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "password_history")
public class PasswordHistoryDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer historyId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Corregir a "user_id"
    private UsuarioDTO userId;

    @Column(name = "hashed_password", nullable = false)
    private String hashedPassword;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
}
