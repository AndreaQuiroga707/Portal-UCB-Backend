package bo.edu.ucb.backend.api;
import bo.edu.ucb.backend.bl.AuthBl;
import bo.edu.ucb.backend.dto.*;
import bo.edu.ucb.backend.entity.Usuarios;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bo.edu.ucb.backend.bl.UsuarioBL;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/usuario")
public class UsuarioAPI {
    private static final Logger LOG = LoggerFactory.getLogger(UsuarioAPI.class); // Agregar el Logger
    private static final Logger loginLogger = LoggerFactory.getLogger("LOGIN_LOGGER");
    @Autowired
    UsuarioBL usuarioBL;
    @Autowired
    private AuthBl authBl;

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> findAllUsuarios() {
        try {
            LOG.info("Obteniendo lista de usuarios"); // Log para obtener la lista de usuarios
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Lista de usuarios");
            response.setData(usuarioBL.findAllUsuarios());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al listar usuarios", e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al listar los usuarios");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findUsuarioById(@PathVariable Integer id) {
        try {
            LOG.info("Obteniendo usuario por ID: {}", id); // Log para obtener un usuario por ID
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Usuario encontrado");
            response.setData(usuarioBL.findUsuarioById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al encontrar el usuario por ID: {}", id, e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al encontrar el usuario");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteUsuarioById(@PathVariable Integer id ) {
        try {
            LOG.info("Eliminando usuario por ID: {}", id); // Log para eliminar un usuario por ID
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Usuario eliminado");
            usuarioBL.deleteUsuarioById(id);
            loginLogger.info("Usuario eliminado por ID: {}", id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al eliminar el usuario por ID: {}", id, e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al eliminar el usuario");
            response.setError(e.getMessage());
            loginLogger.error("Error al eliminar el usuario por ID: {}", id);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updateUsuario(@Valid @RequestBody Usuarios usuarios, BindingResult result) {
        try {
            LOG.info("Actualizando usuario"); // Log para actualizar un usuario
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Usuario actualizado");
            response.setData(usuarioBL.updateUsuario(usuarios, result));
            loginLogger.info("Usuario actualizado con ID: {}, Nombre: '{}'.",
                    usuarios.getUsuarioId(), usuarios.getNombre());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al actualizar el usuario", e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al actualizar el usuario");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Crear usuarios
    @PostMapping("/nuevo")
    public ResponseEntity<UsuarioResponseDto> registrarUsuario(@RequestBody UsuarioRequestDto request) {
        UsuarioResponseDto response = usuarioBL.registrarUsuario(request);
        if (response.getMensaje().equals("Usuario registrado con éxito.")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    // login
    @PostMapping("/auth")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        try {
            LOG.info("Autenticando usuario");

            // Validar entrada
            if (request.getCorreoElectronico() == null || request.getCorreoElectronico().isEmpty()) {
                return ResponseEntity.badRequest().body(new LoginResponseDto("Correo electrónico no proporcionado"));
            }

            if (request.getPassword() == null || request.getPassword().isEmpty()) {
                return ResponseEntity.badRequest().body(new LoginResponseDto("Contraseña no proporcionada"));
            }

            // Intentar autenticar al usuario
            LoginResponseDto response = authBl.authenticate(request);
            LOG.info("Usuario autenticado con éxito");
            loginLogger.info("Usuario autenticado con éxito: {}", request.getCorreoElectronico());
            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            loginLogger.error("Error durante la autenticación: {}", e.getMessage());
            LOG.error("Error durante la autenticación: {}", e.getMessage());
            if (e.getMessage().equals("Usuario no encontrado")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new LoginResponseDto("Usuario no encontrado"));
            } else if (e.getMessage().equals("Contraseña incorrecta")) {
                loginLogger.error("Contraseña incorrecta para: {}", request.getCorreoElectronico());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new LoginResponseDto("Contraseña incorrecta"));
            } else if (e.getMessage().equals("La cuenta está bloqueada. Intente nuevamente más tarde.")) {
                loginLogger.error("La cuenta está bloqueada para: {}", request.getCorreoElectronico());
                return ResponseEntity.status(HttpStatus.LOCKED)
                        .body(new LoginResponseDto("La cuenta está bloqueada. Intente nuevamente más tarde."));
            } else {
                loginLogger.error("Error interno al autenticar el usuario: {}", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new LoginResponseDto("Error interno al autenticar el usuario"));
            }
        }
    }


    @PostMapping("/new/token")
    public ResponseEntity<ResponseDTO> generateResetToken(@RequestBody RequestResetTokenDto email) {
        try {
            LOG.info("Generando token de restablecimiento para: {}", email.getEmail());
            authBl.generateResetToken(email.getEmail());

            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Token de restablecimiento generado y enviado al correo.");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            LOG.error("Error al generar token de restablecimiento: {}", e.getMessage());
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/new/token/password")
    public ResponseEntity<ResponseDTO> resetPassword(@RequestBody ResetPasswordRequestDto request) {
        try {
            LOG.info("Procesando solicitud de restablecimiento de contraseña");
            authBl.resetPasswordWithToken(request.getToken(), request.getNewPassword());
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Contraseña restablecida con éxito.");
            loginLogger.info("Contraseña restablecida con éxito para: {}", request.toString());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            LOG.error("Error al restablecer la contraseña: {}", e.getMessage());
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage(e.getMessage());
            loginLogger.error("Error al restablecer la contraseña: {}", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

}