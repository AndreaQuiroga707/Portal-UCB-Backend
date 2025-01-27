package bo.edu.ucb.backend.dao;

import bo.edu.ucb.backend.dto.UsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDAO extends JpaRepository<UsuarioDTO, Integer> {

    UsuarioDTO findByCorreoElectronico(String email);
    boolean existsByCorreoElectronico(String correoElectronico);
    UsuarioDTO findByUsuarioId(Integer usuarioId);
    UsuarioDTO findByResetToken(String resetToken);

}