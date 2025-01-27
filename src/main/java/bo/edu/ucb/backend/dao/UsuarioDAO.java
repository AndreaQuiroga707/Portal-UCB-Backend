package bo.edu.ucb.backend.dao;

import bo.edu.ucb.backend.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDAO extends JpaRepository<Usuarios, Integer> {

    Usuarios findByCorreoElectronico(String email);
    boolean existsByCorreoElectronico(String correoElectronico);
    Usuarios findByUsuarioId(Integer usuarioId);
    Usuarios findByResetToken(String resetToken);

}