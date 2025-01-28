package bo.edu.ucb.backend.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bo.edu.ucb.backend.bl.SuscripcionesBL;
import bo.edu.ucb.backend.dto.ResponseDTO;
import bo.edu.ucb.backend.entity.Suscripciones;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/suscripciones")
public class SuscripcionesAPI {
    private static final Logger LOG = LoggerFactory.getLogger(SuscripcionesAPI.class);
    private static final Logger appLogger = LoggerFactory.getLogger("APP_LOGGER");
    @Autowired
    private SuscripcionesBL suscripcionesBL;

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> save(@RequestBody Suscripciones correo) {
        ResponseDTO response = new ResponseDTO();
        try {
            LOG.info("Creando suscripción");
            response.setStatus(200);
            response.setMessage("Suscripción creada");
            response.setData(suscripcionesBL.save(correo));
            appLogger.info("Se creó la suscripción con correo: '{}'", correo.getCorreo());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al crear la suscripción {}", e);
            response.setStatus(400);
            response.setMessage("Error al crear la suscripción");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{correo}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable String correo) {
        ResponseDTO response = new ResponseDTO();
        try {
            LOG.info("Eliminando suscripción");
            response.setStatus(200);
            response.setMessage("Suscripción eliminada");
            response.setData(suscripcionesBL.deleteSuscripcionesByEmail(correo));
            appLogger.info("Se eliminó la suscripción con correo: '{}'", correo);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al eliminar la suscripción {}", e);
            response.setStatus(400);
            response.setMessage("Error al eliminar la suscripción");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
