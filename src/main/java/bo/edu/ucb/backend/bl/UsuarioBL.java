package bo.edu.ucb.backend.bl;

import bo.edu.ucb.backend.dto.UsuarioRequestDto;
import bo.edu.ucb.backend.dto.UsuarioResponseDto;
import bo.edu.ucb.backend.security.SHA256PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bo.edu.ucb.backend.dao.UsuarioDAO;
import bo.edu.ucb.backend.dto.UsuarioDTO;
import org.springframework.validation.BindingResult;
import bo.edu.ucb.backend.security.JwtTokenProvider;
@Service
public class UsuarioBL {
    @Autowired
    private UsuarioDAO usuarioDAO;
    private final JwtTokenProvider jwtTokenProvider;
    private final SHA256PasswordEncoder passwordEncoder = new SHA256PasswordEncoder();

    public UsuarioBL(UsuarioDAO usuarioDAO, JwtTokenProvider jwtTokenProvider) {
        this.usuarioDAO = usuarioDAO;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public UsuarioDTO save(UsuarioDTO usuarioDTO, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        return usuarioDAO.save(usuarioDTO);
    }


    // Servicio para registrar usuarios con reglas de contraseña
    public UsuarioResponseDto registrarUsuario(UsuarioRequestDto request) {
        // Verificar si ya existe el correo
        if (usuarioDAO.existsByCorreoElectronico(request.getCorreoElectronico())) {
            return new UsuarioResponseDto("El correo electrónico ya está registrado.", null, null, null, request.getCorreoElectronico());
        }

        // Validar la contraseña según las reglas
        if (!esContrasenaValida(request.getPassword())) {
            return new UsuarioResponseDto("La contraseña debe tener al menos 8 caracteres, una mayúscula, un número y un símbolo.", null, null, null, null);
        }

        // Crear el nuevo usuario
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setCorreoElectronico(request.getCorreoElectronico());
        usuario.setPassword(passwordEncoder.hash(request.getPassword()));

        usuarioDAO.save(usuario);

        String token;
        try {
            // Generar token
            token = jwtTokenProvider.generateToken(usuario.getCorreoElectronico());
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el token");
        }

        // Retornar el DTO con más detalles
        return new UsuarioResponseDto("Usuario registrado con éxito.", token, usuario.getNombre(), usuario.getApellido(), usuario.getCorreoElectronico());
    }

    // Método para validar las contraseñas
    private boolean esContrasenaValida(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(regex);
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

    public void deleteUsuarioById(Integer usuarioId) {
        if (usuarioDAO.findById(usuarioId).isPresent()) {
            usuarioDAO.deleteById(usuarioId);
        } else {
            throw new RuntimeException("Usuario no encontrado");
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
}