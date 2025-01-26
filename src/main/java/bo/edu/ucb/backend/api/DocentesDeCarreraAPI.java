package bo.edu.ucb.backend.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import bo.edu.ucb.backend.bl.DocentesDeCarreraBL;
import bo.edu.ucb.backend.dto.DocentesDeCarreraDTO;
import bo.edu.ucb.backend.dto.ResponseDTO;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/carreras/docentes")
public class DocentesDeCarreraAPI {
    @Autowired
    private DocentesDeCarreraBL docentesDeCarreraBL;

    private static final Logger LOG = LoggerFactory.getLogger(DocentesDeCarreraAPI.class);

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> createDocenteDeCarrera(@RequestBody DocentesDeCarreraDTO docentesDeCarreraDTO) {
        try {
            LOG.info("Creando docente de carrera: {}", docentesDeCarreraDTO);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Docente de carrera creado correctamente");
            response.setData(docentesDeCarreraBL.createDocenteDeCarrera(docentesDeCarreraDTO));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al crear el docente de carrera", e);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al crear el docente de carrera");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updateDocenteDeCarrera(@RequestBody DocentesDeCarreraDTO docentesDeCarreraDTO) {
        try {
            LOG.info("Actualizando docente de carrera: {}", docentesDeCarreraDTO);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Docente de carrera actualizado correctamente");
            response.setData(docentesDeCarreraBL.updateDocenteDeCarrera(docentesDeCarreraDTO));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al actualizar el docente de carrera", e);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al actualizar el docente de carrera");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteDocenteDeCarrera(@RequestBody DocentesDeCarreraDTO docentesDeCarreraDTO) {
        try {
            LOG.info("Eliminando docente de carrera: {}", docentesDeCarreraDTO);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Docente de carrera eliminado correctamente");
            response.setData(docentesDeCarreraBL.deleteDocenteDeCarrera(docentesDeCarreraDTO));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al eliminar el docente de carrera", e);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al eliminar el docente de carrera");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
