package bo.edu.ucb.backend.bl;

import bo.edu.ucb.backend.dao.UsuarioDAO;
import bo.edu.ucb.backend.dto.LoginRequestDto;
import bo.edu.ucb.backend.dto.LoginResponseDto;
import bo.edu.ucb.backend.dto.UsuarioDTO;
import bo.edu.ucb.backend.security.JwtTokenProvider;
import bo.edu.ucb.backend.security.SHA256PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthBl {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthBl.class);
    private final UsuarioDAO userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final SHA256PasswordEncoder passwordEncoder = new SHA256PasswordEncoder();

    public AuthBl(UsuarioDAO userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public LoginResponseDto authenticate(LoginRequestDto request) {
        UsuarioDTO user = userRepository.findByCorreoElectronico(request.getCorreoElectronico());

        LOGGER.info("Usuario encontrado: {}", user);
        LOGGER.info("Contraseña ingresada: {}", request);
        if (user == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        if (!passwordEncoder.hash(request.getPassword()).equals(user.getPassword())) {
            LOGGER.error("Contraseña incorrecta");
            LOGGER.error("Contraseña ingresada: {}", passwordEncoder.hash(request.getPassword()));
            LOGGER.error("Contraseña en BD: {}", user.getPassword());
            throw new RuntimeException("Contraseña incorrecta");
        }

        LOGGER.info("Generando token");

        String token = jwtTokenProvider.generateToken(user.getCorreoElectronico());
        return new LoginResponseDto(token);
    }
}
