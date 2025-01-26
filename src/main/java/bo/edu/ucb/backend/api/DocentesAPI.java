package bo.edu.ucb.backend.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import bo.edu.ucb.backend.bl.DocentesBL;
import bo.edu.ucb.backend.dto.DocentesDTO;
import bo.edu.ucb.backend.dto.ResponseDTO;
import jakarta.validation.Valid;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/docentes")
public class DocentesAPI {
    private static final Logger LOG = LoggerFactory.getLogger(DocentesAPI.class);

    @Autowired
    DocentesBL docentesBL;

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> findAllDocentes() {
        try {
            LOG.info("Obteniendo todos los docentes"); // Log para obtener todos los docentes
            Iterable<DocentesDTO> docentesDTO = docentesBL.findAllDocentes();
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Docentes encontrados");
            response.setData(docentesDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al encontrar los docentes", e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al encontrar los docentes");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findDocenteById(@PathVariable Integer id) {
        try {
            LOG.info("Obteniendo docente por ID: {}", id); // Log para obtener un docente por ID
            DocentesDTO docentesDTO = docentesBL.findDocenteById(id);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Docente encontrado");
            response.setData(docentesDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al encontrar el docente por ID: {}", id, e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al encontrar el docente por ID");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> createDocente(@Valid @RequestBody DocentesDTO docentesDTO, BindingResult result) {
        try {
            LOG.info("Creando Docente"); // Log para crear un docente
            ResponseDTO response = new ResponseDTO();
            response.setStatus(201); // HttpStatus.CREATED
            response.setMessage("Docente creado");
            response.setData(docentesBL.createDocente(docentesDTO, result));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al crear el docente", e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al crear el docente");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updateDocente(@Valid @RequestBody DocentesDTO docentesDTO, BindingResult result) {
        try {
            LOG.info("Actualizando Docente"); // Log para actualizar un docente
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Docente actualizado");
            response.setData(docentesBL.updateDocente(docentesDTO, result));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al actualizar el docente", e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al actualizar el docente");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteDocenteById(@PathVariable Integer id) {
        try {
            LOG.info("Eliminando docente por ID: {}", id); // Log para eliminar un docente por ID
            DocentesDTO docentesDTO = new DocentesDTO();
            docentesDTO.setDocenteId(id);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Docente eliminado");
            response.setData(docentesBL.deleteDocente(docentesDTO));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al eliminar el docente por ID: {}", id, e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al eliminar el docente por ID");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
