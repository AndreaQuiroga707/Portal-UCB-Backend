package bo.edu.ucb.backend.bl;

import bo.edu.ucb.backend.dao.PasswordHistoryDAO;
import bo.edu.ucb.backend.dao.UsuarioDAO;
import bo.edu.ucb.backend.dto.LoginRequestDto;
import bo.edu.ucb.backend.dto.LoginResponseDto;
import bo.edu.ucb.backend.entity.PasswordHistory;
import bo.edu.ucb.backend.entity.Usuarios;
import bo.edu.ucb.backend.security.JwtTokenProvider;
import bo.edu.ucb.backend.security.SHA256PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class AuthBl {
    @Autowired
    private PasswordHistoryDAO passwordHistoryDAO;
    @Autowired
    private EmailSenderBL emailService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthBl.class);
    private final UsuarioDAO userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final SHA256PasswordEncoder passwordEncoder = new SHA256PasswordEncoder();

    public AuthBl(UsuarioDAO userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public LoginResponseDto authenticate(LoginRequestDto request) {
        Usuarios user = userRepository.findByCorreoElectronico(request.getCorreoElectronico());

        if (user == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        if (user.getIsLocked() && isAccountLocked(user)) {
            throw new RuntimeException("La cuenta está bloqueada. Intente nuevamente más tarde.");
        }

        if (!passwordEncoder.hash(request.getPassword()).equals(user.getPassword())) {
            handleFailedLogin(user);
            throw new RuntimeException("Contraseña incorrecta");
        }

        // Verificar si la contraseña ha expirado
        if (isPasswordExpired(user.getLastPasswordUpdate())) {
            throw new RuntimeException("Debe cambiar su contraseña. Ha pasado más de 60 días desde la última actualización.");
        }

        resetFailedAttempts(user);

        String token = jwtTokenProvider.generateToken(user.getCorreoElectronico(), user.getRol().getNombre());
        return new LoginResponseDto(token);
    }


    private boolean isPasswordExpired(Date lastPasswordUpdate) {
        long maxDuration = TimeUnit.DAYS.toMillis(60); // 60 días
        return System.currentTimeMillis() - lastPasswordUpdate.getTime() > maxDuration;
    }



    private void handleFailedLogin(Usuarios user) {
        user.setFailedAttempts(user.getFailedAttempts() + 1);
        if (user.getFailedAttempts() >= 3) {
            user.setIsLocked(true);
            user.setLockTime(new Timestamp(new Date().getTime()));
        }
        userRepository.save(user);
    }

    private boolean isAccountLocked(Usuarios user) {
        long lockDuration = TimeUnit.MINUTES.toMillis(30);
        return System.currentTimeMillis() - user.getLockTime().getTime() < lockDuration;
    }

    private void resetFailedAttempts(Usuarios user) {
        user.setFailedAttempts(0);
        user.setIsLocked(false);
        userRepository.save(user);
    }

    public void generateResetToken(String email) {
        Usuarios user = userRepository.findByCorreoElectronico(email);

        if (user == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        if (!user.getIsLocked()) {
            throw new RuntimeException("La cuenta no está bloqueada");
        }

        String token = UUID.randomUUID().toString();
        Date expiration = new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1)); // 1 hora de validez

        user.setResetToken(token);
        user.setResetTokenExpiration(new Timestamp(expiration.getTime()));
        userRepository.save(user);

        emailService.sendPasswordResetEmail(user.getCorreoElectronico(), token);
    }

    public void resetPasswordWithToken(String token, String newPassword) {
        Usuarios user = userRepository.findByResetToken(token);

        if (user == null || user.getResetTokenExpiration().before(new Date())) {
            throw new RuntimeException("Token inválido o expirado");
        }

        // Validar la nueva contraseña
        if (!esContrasenaValida(newPassword)) {
            throw new RuntimeException("La nueva contraseña no cumple con las reglas de seguridad");
        }

        // Verificar que no coincida con las últimas 6 contraseñas
        if (isPasswordInHistory(user.getUsuarioId(), newPassword)) {
            throw new RuntimeException("La nueva contraseña no puede coincidir con ninguna de las últimas 6 contraseñas.");
        }

        // Actualizar la contraseña
        String hashedPassword = passwordEncoder.hash(newPassword);
        user.setPassword(hashedPassword);
        user.setLastPasswordUpdate(new Date());
        user.setResetToken(null);
        user.setResetTokenExpiration(null);
        user.setIsLocked(false);
        user.setFailedAttempts(0);
        user.setLockTime(null);

        // Guardar la contraseña en el historial
        savePasswordToHistory(user.getUsuarioId(), hashedPassword);

        userRepository.save(user);
    }


    private boolean isPasswordInHistory(int userId, String newPassword) {
        List<String> oldPasswords = passwordHistoryDAO.findLast6PasswordsByUserId(userId);
        String hashedNewPassword = passwordEncoder.hash(newPassword);

        return oldPasswords.contains(hashedNewPassword);
    }

    private void savePasswordToHistory(int userId, String hashedPassword) {
        PasswordHistory history = new PasswordHistory();
        Usuarios user;
        user = userRepository.findByUsuarioId(userId);
        history.setUserId(user);
        history.setHashedPassword(hashedPassword);
        history.setCreatedAt(new Timestamp(new Date().getTime()));
        passwordHistoryDAO.save(history);
    }


    private boolean esContrasenaValida(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(regex);
    }

}
