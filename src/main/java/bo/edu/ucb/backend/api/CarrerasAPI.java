package bo.edu.ucb.backend.api;

import bo.edu.ucb.backend.bl.CarrerasBL;
import bo.edu.ucb.backend.entity.Carreras;
import bo.edu.ucb.backend.dto.ResponseDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/carreras")
public class CarrerasAPI {
    private static final Logger LOG = LoggerFactory.getLogger(CarrerasAPI.class);

    @Autowired
    private CarrerasBL carrerasBL;

    @GetMapping("/nombre/{nombreCarrera}")
    public ResponseEntity<?> findCarreraByNombre(@PathVariable String nombreCarrera) {
        try {
            Carreras carrera = carrerasBL.findCarreraByNombre(nombreCarrera);
            if(carrera == null) {
                return ResponseEntity.notFound().build(); // Devuelve un 404 si la carrera no se encuentra
            }
            return ResponseEntity.ok(carrera); // Devuelve un 200 con los datos de la carrera
        } catch (Exception e) {
            LOG.error("Error al encontrar carrera por nombre: {}", nombreCarrera, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Devuelve un 500 en caso de error
        }
    }

    @GetMapping("/facultad/{facultadId}")
    public ResponseEntity<?> findCarrerasByFacultadId(@PathVariable Integer facultadId) {
        try {
            List<Carreras> carreras = carrerasBL.findCarrerasByFacultadId(facultadId);
            if(carreras.isEmpty()) {
                return ResponseEntity.notFound().build(); // Devuelve un 404 si la lista está vacía
            }
            return ResponseEntity.ok(carreras); // Devuelve un 200 con la lista de carreras
        } catch (Exception e) {
            LOG.error("Error al encontrar carreras por ID de facultad: {}", facultadId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Devuelve un 500 en caso de error
        }
    }

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> findAllCarreras() {
        try {
            LOG.info("Obteniendo lista de carreras");
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Lista de carreras");
            response.setData(carrerasBL.findAllCarreras());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al listar las carreras", e);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al listar las carreras");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findCarrerasById(@PathVariable Integer id) {
        try {
            LOG.info("Buscando carrera con ID: {}", id);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Carrera encontrada");
            response.setData(carrerasBL.findCarrerasById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al encontrar la carrera", e);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al encontrar la carrera");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> saveCarreras(@Valid @RequestBody Carreras carreras, BindingResult result) {
        try {
            LOG.info("Guardando carrera");
            System.out.println("acreditacion: " + carreras.toString());
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Carrera guardada");
            response.setData(carrerasBL.save(carreras, result));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al guardar la carrera", e);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al guardar la carrera");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteCarrerasById(@PathVariable Integer id) {
        try {
            LOG.info("Eliminando carrera con ID: {}", id);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Carrera eliminada");
            response.setData(carrerasBL.deleteCarrerasById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al eliminar la carrera", e);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al eliminar la carrera");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updateCarreras(@Valid @RequestBody Carreras carreras, BindingResult result) {
        try {
            LOG.info("Actualizando carrera");
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Carrera actualizada");
            response.setData(carrerasBL.updateCarreras(carreras, result));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al actualizar la carrera", e);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al actualizar la carrera");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}