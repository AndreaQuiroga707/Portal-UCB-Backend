package bo.edu.ucb.backend.api;

import bo.edu.ucb.backend.bl.CentrosInvestigacionBL;
import bo.edu.ucb.backend.dto.CentrosInvestigacionDTO;
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
@RequestMapping("/api/v1/centros/investigaciones")
public class CentrosInvestigacionAPI {
    private static final Logger LOG = LoggerFactory.getLogger(CentrosInvestigacionAPI.class);
    private static final Logger appLogger = LoggerFactory.getLogger("APP_LOGGER");
    @Autowired
    private CentrosInvestigacionBL centrosInvestigacionBL;

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> findAllInstitutosInvestigacion() {
        ResponseDTO response = new ResponseDTO();
        try{
            LOG.info("Obteniendo lista de Centros de investigación");
            response.setStatus(200);
            response.setMessage("Lista de Centros de investigación");
            response.setData(centrosInvestigacionBL.findAllInstitutosInvestigacion());
            appLogger.info("Lista de Centros de investigación");
            return ResponseEntity.ok(response);
        } catch (Exception e){
            LOG.error("Error al listar los Centros de investigación");
            response.setStatus(400);
            response.setMessage("Error al listar los Centros de investigación");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findInstitutoInvestigacionById(@PathVariable Integer id) {
        ResponseDTO response = new ResponseDTO();
        try{
            LOG.info("Buscando Centro de investigación con ID: {}", id);
            response.setStatus(200);
            response.setMessage("Centro de investigación encontrado");
            response.setData(centrosInvestigacionBL.findInstitutoInvestigacionById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e){
            LOG.error("Error al encontrar el Centro de investigación");
            response.setStatus(400);
            response.setMessage("Error al encontrar el Centro de investigación");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> save(@Valid @RequestBody CentrosInvestigacionDTO centrosInvestigacionDTO, BindingResult result) {
        ResponseDTO response = new ResponseDTO();
        try{
            LOG.info("Guardando Centro de investigación");
            LOG.info("Centro {}",centrosInvestigacionDTO.toString());
            response.setStatus(200);
            response.setMessage("Centro de investigación guardado");
            response.setData(centrosInvestigacionBL.save(centrosInvestigacionDTO, result));
            appLogger.info("Se creó el Centro de investigación con ID: {}, Nombre: '{}'.",
                    centrosInvestigacionDTO.getIdCentroInvestigacion(), centrosInvestigacionDTO.getNombre());
            return ResponseEntity.ok(response);
        } catch (Exception e){
            LOG.error("Error al guardar el Centro de investigación {}",e);
            response.setStatus(400);
            response.setMessage("Error al guardar el Centro de investigación");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteInstitutoInvestigacionById(@PathVariable Integer id) {
        ResponseDTO response = new ResponseDTO();
        try{
            LOG.info("Eliminando Centro de investigación con ID: {}", id);
            response.setStatus(200);
            response.setMessage("Centro de investigación eliminado");
            response.setData(centrosInvestigacionBL.deleteInstitutoInvestigacionById(id));
            appLogger.info("Se eliminó el Centro de investigación con ID: {}", id);
            return ResponseEntity.ok(response);
        } catch (Exception e){
            LOG.error("Error al eliminar el Centro de investigación");
            response.setStatus(400);
            response.setMessage("Error al eliminar el Centro de investigación");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updateInstitutoInvestigacion(@Valid @RequestBody CentrosInvestigacionDTO centrosInvestigacionDTO, BindingResult result) {
        ResponseDTO response = new ResponseDTO();
        LOG.info("xd {}",centrosInvestigacionDTO.toString());
        try{
            LOG.info("Actualizando Centro de investigación");
            response.setStatus(200);
            response.setMessage("Centro de investigación actualizado");
            response.setData(centrosInvestigacionBL.updateInstitutoInvestigacion(centrosInvestigacionDTO, result));
            appLogger.info("Se actualizó el Centro de investigación con ID: {}, Nombre: '{}'.",
                    centrosInvestigacionDTO.getIdCentroInvestigacion(), centrosInvestigacionDTO.getNombre());
            return ResponseEntity.ok(response);
        } catch (Exception e){
            LOG.error("Error al actualizar el Centro de investigación {}", e);
            response.setStatus(400);
            response.setMessage("Error al actualizar el Centro de investigación");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

}
