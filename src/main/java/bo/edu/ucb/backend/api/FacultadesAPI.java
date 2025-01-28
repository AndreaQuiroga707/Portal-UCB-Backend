package bo.edu.ucb.backend.api;

import bo.edu.ucb.backend.bl.FacultadesBL;
import bo.edu.ucb.backend.dto.FacultadesDTO;
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
@RequestMapping("/api/v1/facultades")
public class FacultadesAPI {
    private static final Logger LOG = LoggerFactory.getLogger(FacultadesAPI.class);
    private static final Logger appLogger = LoggerFactory.getLogger("APP_LOGGER");
    @Autowired
    FacultadesBL facultadesBL;

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> createEventos(@Valid @RequestBody FacultadesDTO facultadesDTO, BindingResult result) {
        try {
            LOG.info("Creando Facultad"); // Log para crear un evento
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("facultad creada");
            response.setData(facultadesBL.save(facultadesDTO, result));
            appLogger.info("Se creó la facultad con ID: {}, Nombre: '{}'.",
                    facultadesDTO.getFacultadId(), facultadesDTO.getNombre());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al crear la facultad", e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al crear la facultad");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findFacultadesById(@PathVariable Integer id) {
        try {
            LOG.info("Obteniendo facultad por ID: {}", id); // Log para obtener una facultad por ID
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Facultad encontrada");
            response.setData(facultadesBL.findFacultadesById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al encontrar la facultad por ID: {}", id, e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al encontrar la facultad");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }


    @GetMapping("/")
    public ResponseEntity<ResponseDTO> findAllFacultades() {
        try {
            LOG.info("Obteniendo lista de facultades"); // Log para obtener la lista de facultades
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Lista de facultades");
            response.setData(facultadesBL.findAllFacultades());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al listar facultades", e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al listar las facultades");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }


    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updateFacultades(@Valid @RequestBody FacultadesDTO facultadesDTO, BindingResult result) {
        try {
            LOG.info("Actualizando facultad"); // Log para actualizar una facultad por ID
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Facultad actualizada");
            response.setData(facultadesBL.updateFacultades(facultadesDTO, result));
            appLogger.info("Se actualizó la facultad con ID: {}, Nombre: '{}'.",
                    facultadesDTO.getFacultadId(), facultadesDTO.getNombre());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al actualizar la facultad", e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al actualizar la facultad");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteFacultadesById(@PathVariable Integer id) {
        try {
            LOG.info("Eliminando facultad por ID: {}", id); // Log para eliminar una facultad por ID
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Facultad eliminada");
            facultadesBL.deleteFacultadesById(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al eliminar la facultad por ID: {}", id, e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al eliminar la facultad");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

}