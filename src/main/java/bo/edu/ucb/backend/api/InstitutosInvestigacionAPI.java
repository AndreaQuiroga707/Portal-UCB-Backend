package bo.edu.ucb.backend.api;

import bo.edu.ucb.backend.bl.InstitutosInvestigacionBL;
import bo.edu.ucb.backend.dto.InstitutosInvestigacionDTO;
import bo.edu.ucb.backend.dto.ResponseDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/institutos/investigacion")
public class InstitutosInvestigacionAPI {
    private static final Logger LOG = LoggerFactory.getLogger(InstitutosInvestigacionAPI.class);

    @Autowired
    private InstitutosInvestigacionBL institutosInvestigacionBL;

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> findAllInstitutosInvestigacion() {
        ResponseDTO response = new ResponseDTO();
        try{
            LOG.info("Obteniendo lista de institutos de investigación");
            response.setStatus(200);
            response.setMessage("Lista de institutos de investigación");
            response.setData(institutosInvestigacionBL.findAllInstitutosInvestigacion());
            return ResponseEntity.ok(response);
        } catch (Exception e){
            LOG.error("Error al listar los institutos de investigación");
            response.setStatus(400);
            response.setMessage("Error al listar los institutos de investigación");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findInstitutoInvestigacionById(@PathVariable Integer id) {
        ResponseDTO response = new ResponseDTO();
        try{
            LOG.info("Buscando instituto de investigación con ID: {}", id);
            response.setStatus(200);
            response.setMessage("Instituto de investigación encontrado");
            response.setData(institutosInvestigacionBL.findInstitutoInvestigacionById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e){
            LOG.error("Error al encontrar el instituto de investigación");
            response.setStatus(400);
            response.setMessage("Error al encontrar el instituto de investigación");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> saveInstitutoInvestigacion(@Valid @RequestBody InstitutosInvestigacionDTO institutosInvestigacionDTO, BindingResult result) {
        ResponseDTO response = new ResponseDTO();
        try{
            LOG.info("Guardando instituto de investigación");
            response.setStatus(200);
            response.setMessage("Instituto de investigación guardado");
            response.setData(institutosInvestigacionBL.save(institutosInvestigacionDTO, result));
            return ResponseEntity.ok(response);
        } catch (Exception e){
            LOG.error("Error al guardar el instituto de investigación {}", e);
            response.setStatus(400);
            response.setMessage("Error al guardar el instituto de investigación");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteInstitutoInvestigacionById(@PathVariable Integer id) {
        ResponseDTO response = new ResponseDTO();
        try{
            LOG.info("Eliminando instituto de investigación con ID: {}", id);
            response.setStatus(200);
            response.setMessage("Instituto de investigación eliminado");
            response.setData(institutosInvestigacionBL.deleteInstitutoInvestigacionById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e){
            LOG.error("Error al eliminar el instituto de investigación");
            response.setStatus(400);
            response.setMessage("Error al eliminar el instituto de investigación");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updateInstitutoInvestigacion(@Valid @RequestBody InstitutosInvestigacionDTO institutosInvestigacionDTO, BindingResult result) {
        ResponseDTO response = new ResponseDTO();
        try{
            LOG.info("Actualizando instituto de investigación");
            response.setStatus(200);
            response.setMessage("Instituto de investigación actualizado");
            response.setData(institutosInvestigacionBL.updateInstitutoInvestigacion(institutosInvestigacionDTO, result));
            return ResponseEntity.ok(response);
        } catch (Exception e){
            LOG.error("Error al actualizar el instituto de investigación {}", e);
            response.setStatus(400);
            response.setMessage("Error al actualizar el instituto de investigación");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

}
