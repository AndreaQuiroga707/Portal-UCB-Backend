package bo.edu.ucb.backend.api;

import bo.edu.ucb.backend.bl.ProgramaAcademicoBL;
import bo.edu.ucb.backend.dto.ProgramaAcademicoDTO;
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
@RequestMapping("/api/v1/programas_academicos")
public class ProgramaAcademicoAPI {
    private static final Logger LOG = LoggerFactory.getLogger(ProgramaAcademicoAPI.class);
    private static final Logger appLogger = LoggerFactory.getLogger("APP_LOGGER");
    @Autowired
    ProgramaAcademicoBL programaAcademicoBL;

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> createProgramaAcademico(@RequestBody ProgramaAcademicoDTO programaAcademicoDTO, BindingResult result) {
        try {
            LOG.info("Creando Programa Académico"); // Log para crear un programa académico
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Programa Académico creado");
            response.setData(programaAcademicoBL.save(programaAcademicoDTO, result));
            appLogger.info("Se creó el Programa Académico con ID: {}, Nombre: '{}'.",
                    programaAcademicoDTO.getProgramaId(), programaAcademicoDTO.getNombre());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al crear el Programa Académico: {}", e.getMessage()); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al crear el Programa Académico");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }


    @GetMapping("/{programaId}")
    public ResponseEntity<ResponseDTO> findProgramaAcademicoById(@PathVariable Integer programaId) {
        try {
            LOG.info("Obteniendo Programa Académico por ID: {}", programaId); // Log para obtener un programa académico por ID
            ProgramaAcademicoDTO programaAcademico = programaAcademicoBL.findProgramaAcademicoById(programaId);
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Programa Académico encontrado");
            response.setData(programaAcademico);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al obtener el Programa Académico por ID: {}", programaId, e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al obtener el Programa Académico");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> findAllProgramasAcademicos() {
        try {
            LOG.info("Obteniendo lista de Programas Académicos"); // Log para obtener la lista de programas académicos
            Iterable<ProgramaAcademicoDTO> programasAcademicos = programaAcademicoBL.findAllProgramasAcademicos();
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Lista de Programas Académicos encontrada");
            response.setData(programasAcademicos);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al obtener todos los Programas Académicos", e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al obtener todos los Programas Académicos");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updateProgramaAcademico(@Valid @RequestBody ProgramaAcademicoDTO programaAcademicoDTO, BindingResult result) {
        try {
            LOG.info("Actualizando Programa Académico"); // Log para actualizar un programa académico por ID
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Programa Académico actualizado");
            response.setData(programaAcademicoBL.updateProgramaAcademico(programaAcademicoDTO, result));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al actualizar el Programa Académico", e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al actualizar el Programa Académico");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{programaId}")
    public ResponseEntity<ResponseDTO> deleteProgramaAcademico(@PathVariable Integer programaId) {
        try {
            LOG.info("Eliminando Programa Académico por ID: {}", programaId); // Log para eliminar un programa académico por ID
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Programa Académico eliminado");
            programaAcademicoBL.deleteProgramaAcademicoById(programaId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al eliminar el Programa Académico por ID: {}", programaId, e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al eliminar el Programa Académico");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}