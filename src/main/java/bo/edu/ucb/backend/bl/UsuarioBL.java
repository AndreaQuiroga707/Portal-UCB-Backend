package bo.edu.ucb.backend.bl;

import bo.edu.ucb.backend.dao.PasswordHistoryDAO;
import bo.edu.ucb.backend.dto.PasswordHistoryDTO;
import bo.edu.ucb.backend.dto.UsuarioRequestDto;
import bo.edu.ucb.backend.dto.UsuarioResponseDto;
import bo.edu.ucb.backend.security.SHA256PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bo.edu.ucb.backend.dao.UsuarioDAO;
import bo.edu.ucb.backend.dto.UsuarioDTO;
import org.springframework.validation.BindingResult;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UsuarioBL {

    @Autowired
    private PasswordHistoryDAO passwordHistoryDAO;

    @Autowired
    private UsuarioDAO usuarioDAO;

    private final SHA256PasswordEncoder passwordEncoder = new SHA256PasswordEncoder();

    public UsuarioDTO save(UsuarioDTO usuarioDTO, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        return usuarioDAO.save(usuarioDTO);
    }

    public void deleteUsuarioById(Integer usuarioId) {
        if (usuarioDAO.findById(usuarioId).isPresent()) {
            usuarioDAO.deleteById(usuarioId);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    public UsuarioDTO findUsuarioById(Integer usuarioId) {
        return usuarioDAO.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Iterable<UsuarioDTO> findAllUsuarios() {
        try {
            return usuarioDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los usuarios");
        }
    }

    public UsuarioDTO updateUsuario(UsuarioDTO usuarioDTO, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        if (usuarioDAO.findById(usuarioDTO.getUsuarioId()).isPresent()) {
            return usuarioDAO.save(usuarioDTO);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    public UsuarioResponseDto registrarUsuario(UsuarioRequestDto request) {
        if (usuarioDAO.existsByCorreoElectronico(request.getCorreoElectronico())) {
            return new UsuarioResponseDto("El correo electrónico ya está registrado.", null, null, null, request.getCorreoElectronico());
        }

        if (!esContrasenaValida(request.getPassword())) {
            return new UsuarioResponseDto("La contraseña no cumple con las reglas de seguridad.", null, null, null, null);
        }

        // Crear el nuevo usuario
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setCorreoElectronico(request.getCorreoElectronico());

        // Hashear la contraseña y asignarla al usuario
        String hashedPassword = passwordEncoder.hash(request.getPassword());
        usuario.setPassword(hashedPassword);
        usuario.setLastPasswordUpdate(new Date());

        // Guardar el usuario en la base de datos
        usuarioDAO.save(usuario);

        // Guardar la contraseña inicial en el historial
        savePasswordHistory(usuario.getUsuarioId(), hashedPassword);

        return new UsuarioResponseDto("Usuario registrado con éxito.", null, usuario.getNombre(), usuario.getApellido(), usuario.getCorreoElectronico());
    }

//    public String changePassword(int userId, String newPassword) {
//        UsuarioDTO user = usuarioDAO.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
//
//        if (isPasswordExpired(user.getLastPasswordUpdate())) {
//            throw new RuntimeException("La contraseña ha expirado. Debe cambiarla.");
//        }
//
//        if (isPasswordInHistory(userId, newPassword)) {
//            throw new RuntimeException("No puede reutilizar una contraseña anterior.");
//        }
//
//        String hashedPassword = passwordEncoder.hash(newPassword);
//        user.setPassword(hashedPassword);
//        user.setLastPasswordUpdate(new Date());
//        usuarioDAO.save(user);
//        savePasswordHistory(userId, hashedPassword);
//
//        return "Contraseña actualizada con éxito.";
//    }

    private boolean esContrasenaValida(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(regex);
    }

//    public boolean isPasswordExpired(Date lastPasswordUpdate) {
//        long maxDuration = TimeUnit.DAYS.toMillis(90);
//        return System.currentTimeMillis() - lastPasswordUpdate.getTime() > maxDuration;
//    }
//
//    public boolean isPasswordInHistory(int userId, String newPassword) {
//        List<String> oldPasswords = passwordHistoryDAO.findLast6PasswordsByUserId(userId);
//        String hashedNewPassword = passwordEncoder.hash(newPassword);
//        return oldPasswords.contains(hashedNewPassword);
//    }

    private void savePasswordHistory(int userId, String hashedPassword) {
        PasswordHistoryDTO history = new PasswordHistoryDTO();

        // Buscar el usuario por ID
        UsuarioDTO user = usuarioDAO.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Configurar los valores del historial
        history.setUserId(user); // Usa "user_id"
        history.setHashedPassword(hashedPassword);
        history.setCreatedAt(new Timestamp(new Date().getTime()));

        // Guardar en la base de datos
        passwordHistoryDAO.save(history);
    }

}
