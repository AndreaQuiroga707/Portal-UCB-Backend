package bo.edu.ucb.backend.api;

import bo.edu.ucb.backend.bl.EmailSenderBL;
import bo.edu.ucb.backend.bl.EventosBL;
import bo.edu.ucb.backend.entity.Eventos;
import bo.edu.ucb.backend.dto.ResponseDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/eventos")
public class EventosAPI {
    private static final Logger LOG = LoggerFactory.getLogger(EventosAPI.class); // Agregar el Logger
    private static final Logger appLogger = LoggerFactory.getLogger("APP_LOGGER");
    @Autowired
    EventosBL eventosBL;
    @Autowired
    private EmailSenderBL emailSenderBL;

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> findAllEventos() {
        try {
            LOG.info("Obteniendo lista de eventos"); // Log para obtener la lista de eventos
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Lista de eventos");
            response.setData(eventosBL.findAllEventos());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al listar eventos", e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al listar los eventos");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findEventosById(@PathVariable Integer id) {
        try {
            LOG.info("Obteniendo evento por ID: {}", id); // Log para obtener un evento por ID
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Evento encontrado");
            response.setData(eventosBL.findEventosById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al encontrar el evento por ID: {}", id, e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al encontrar el evento");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> createEventos(@Valid @RequestBody Eventos eventos, BindingResult result) {
        try {
            LOG.info("Creando evento"); // Log para crear un evento
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Evento creado");
            response.setData(eventosBL.save(eventos, result));
            emailSenderBL.sendEmailSuscripcion(eventos.getNombre(), eventos.getDescripcion());
            appLogger.info("Se creó el evento con ID: {}, Nombre: '{}'.",
                    eventos.getEventoId(), eventos.getNombre());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al crear el evento", e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al crear el evento");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteEventosById(@PathVariable Integer id) {
        try {
            LOG.info("Eliminando evento por ID: {}", id); // Log para eliminar un evento por ID
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Evento eliminado");
            eventosBL.deleteEventosById(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al eliminar el evento por ID: {}", id, e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al eliminar el evento");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updateEventos(@Valid @RequestBody Eventos eventos, BindingResult result) {
        try {
            LOG.info("Actualizando evento"); // Log para actualizar un evento
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Evento actualizado");
            response.setData(eventosBL.updateEventos(eventos, result));
            appLogger.info("Se actualizó el evento con ID: {}, Nombre: '{}'.",
                    eventos.getEventoId(), eventos.getNombre());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al actualizar el evento", e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al actualizar el evento");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}