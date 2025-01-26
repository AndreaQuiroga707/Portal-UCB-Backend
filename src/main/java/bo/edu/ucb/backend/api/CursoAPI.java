package bo.edu.ucb.backend.api;

import bo.edu.ucb.backend.bl.CursoBL;
import bo.edu.ucb.backend.dto.CursoDTO;
import bo.edu.ucb.backend.dto.ResponseDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/cursos")
public class CursoAPI {
    private static final Logger LOG = LoggerFactory.getLogger(CursoAPI.class);

    @Autowired
    CursoBL cursoBL;

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> createCurso(@Valid @RequestBody CursoDTO cursoDTO, BindingResult result) {
        try {
            LOG.info("Creando Curso"); // Log para crear un curso
            ResponseDTO response = new ResponseDTO();
            response.setStatus(201); // HttpStatus.CREATED
            response.setMessage("Curso creado");
            response.setData(cursoBL.saveCurso(cursoDTO, result));
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            LOG.error("Error al crear el curso", e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al crear el curso");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findCursoById(@PathVariable Integer id) {
        try {
            LOG.info("Obteniendo curso por ID: {}", id); // Log para obtener un curso por ID
            CursoDTO cursoDTO = cursoBL.findCursoById(id);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Curso encontrado");
            response.setData(cursoDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al encontrar el curso por ID: {}", id, e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al encontrar el curso");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> findAllCursos() {
        try {
            LOG.info("Obteniendo lista de cursos"); // Log para obtener la lista de cursos
            Iterable<CursoDTO> cursos = cursoBL.findAllCursos();
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Lista de cursos encontrada");
            response.setData(cursos);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al listar cursos", e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al listar los cursos");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateCurso(@PathVariable Integer id, @Valid @RequestBody CursoDTO cursoDTO, BindingResult result) {
        try {
            cursoDTO.setCursoId(id);
            LOG.info("Actualizando curso por ID: {}", id); // Log para actualizar un curso por ID
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Curso actualizado");
            response.setData(cursoBL.updateCurso(cursoDTO, result));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al actualizar el curso por ID: {}", id, e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al actualizar el curso");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteCurso(@PathVariable Integer id) {
        try {
            LOG.info("Eliminando curso por ID: {}", id); // Log para eliminar un curso por ID
            cursoBL.deleteCursoById(id);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Curso eliminado exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al eliminar el curso por ID: {}", id, e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al eliminar el curso");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

}