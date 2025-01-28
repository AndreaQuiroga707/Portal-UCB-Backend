package bo.edu.ucb.backend.bl;

import bo.edu.ucb.backend.dao.PasswordHistoryDAO;
import bo.edu.ucb.backend.dao.RolesDAO;
import bo.edu.ucb.backend.entity.PasswordHistory;
import bo.edu.ucb.backend.dto.UsuarioRequestDto;
import bo.edu.ucb.backend.dto.UsuarioResponseDto;
import bo.edu.ucb.backend.entity.Roles;
import bo.edu.ucb.backend.security.SHA256PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bo.edu.ucb.backend.dao.UsuarioDAO;
import bo.edu.ucb.backend.entity.Usuarios;
import org.springframework.validation.BindingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class UsuarioBL {
    @Autowired
    private RolesDAO rolesDAO;
    @Autowired
    private PasswordHistoryDAO passwordHistoryDAO;

    @Autowired
    private UsuarioDAO usuarioDAO;

    private final SHA256PasswordEncoder passwordEncoder = new SHA256PasswordEncoder();

    public Usuarios save(Usuarios usuarios, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        return usuarioDAO.save(usuarios);
    }

    public void deleteUsuarioById(Integer usuarioId) {
        if (usuarioDAO.findById(usuarioId).isPresent()) {
            usuarioDAO.deleteById(usuarioId);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    public Usuarios findUsuarioById(Integer usuarioId) {
        return usuarioDAO.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Iterable<Usuarios> findAllUsuarios() {
        try {
            return usuarioDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los usuarios");
        }
    }

    public Usuarios updateUsuario(Usuarios usuarios, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        if (usuarioDAO.findById(usuarios.getUsuarioId()).isPresent()) {
            return usuarioDAO.save(usuarios);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    public UsuarioResponseDto registrarUsuario(UsuarioRequestDto request) {
        try {
            // Verificar si el correo ya está registrado
            if (usuarioDAO.existsByCorreoElectronico(request.getCorreoElectronico())) {
                throw new IllegalArgumentException("El correo electrónico ya está registrado.");
            }

            // Validar la contraseña
            if (!esContrasenaValida(request.getPassword())) {
                throw new IllegalArgumentException("La contraseña no cumple con las reglas de seguridad.");
            }

            // Crear el nuevo usuario
            Usuarios usuario = new Usuarios();
            usuario.setNombre(request.getNombre());
            usuario.setApellido(request.getApellido());
            usuario.setCorreoElectronico(request.getCorreoElectronico());
            usuario.setPassword(passwordEncoder.hash(request.getPassword()));
            usuario.setLastPasswordUpdate(new Date());

            // Asignar rol predeterminado o basado en el request
            Roles rol = rolesDAO.findByNombre(request.getRol());
            if (rol == null) {
                throw new IllegalArgumentException("El rol especificado no existe.");
            }
            usuario.setRol(rol);

            // Guardar el usuario en la base de datos
            usuarioDAO.save(usuario);

            // Guardar la contraseña en el historial
            savePasswordHistory(usuario.getUsuarioId(), usuario.getPassword());

            // Retornar respuesta de éxito
            return new UsuarioResponseDto("Usuario registrado con éxito.", null, usuario.getNombre(), usuario.getApellido(), usuario.getCorreoElectronico());

        } catch (IllegalArgumentException e) {
            // Manejo de errores de validación
            return new UsuarioResponseDto(e.getMessage(), null, null, null, null);
        } catch (Exception e) {
            // Manejo de errores inesperados
            throw new RuntimeException("Error al registrar el usuario: " + e.getMessage());
        }
    }




    private boolean esContrasenaValida(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(regex);
    }

    private void savePasswordHistory(int userId, String hashedPassword) {
        PasswordHistory history = new PasswordHistory();

        // Buscar el usuario por ID
        Usuarios user = usuarioDAO.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Configurar los valores del historial
        history.setUserId(user); // Usa "user_id"
        history.setHashedPassword(hashedPassword);
        history.setCreatedAt(new Timestamp(new Date().getTime()));

        // Guardar en la base de datos
        passwordHistoryDAO.save(history);
    }

}
