package bo.edu.ucb.backend.api;

import bo.edu.ucb.backend.bl.GruposInvestigacionBL;
import bo.edu.ucb.backend.dto.GruposInvestigacionDTO;
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
@RequestMapping("/api/v1/grupos/investigaciones")
public class GruposInvestigacionAPI {
    private static final Logger LOG = LoggerFactory.getLogger(GruposInvestigacionAPI.class);
    private static final Logger appLogger = LoggerFactory.getLogger("APP_LOGGER");

    @Autowired
    private GruposInvestigacionBL gruposInvestigacionBL;

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> findAllGruposInvestigacion() {
        ResponseDTO response = new ResponseDTO();
        try{
            LOG.info("Obteniendo lista de Grupos de Investigacion");
            response.setStatus(200);
            response.setMessage("Lista de Grupos de Investigacion");
            response.setData(gruposInvestigacionBL.findAllGruposInvestigacion());
            return ResponseEntity.ok(response);
        } catch (Exception e){
            LOG.error("Error al listar los Grupos de Investigacion");
            response.setStatus(400);
            response.setMessage("Error al listar los Grupos de Investigacion");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findGruposInvestigacionById(@PathVariable Integer id) {
        ResponseDTO response = new ResponseDTO();
        try{
            LOG.info("Buscando Grupo de Investigacion con ID: {}", id);
            response.setStatus(200);
            response.setMessage("Grupo de Investigacion encontrado");
            response.setData(gruposInvestigacionBL.findGruposInvestigacionById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e){
            LOG.error("Error al encontrar el Grupo de Investigacion");
            response.setStatus(400);
            response.setMessage("Error al encontrar el Grupo de Investigacion");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> createGruposInvestigacion(@Valid @RequestBody GruposInvestigacionDTO gruposInvestigacionDTO, BindingResult result) {
        ResponseDTO response = new ResponseDTO();
        try{
            LOG.info("Creando Grupo de Investigacion");
            response.setStatus(200);
            response.setMessage("Grupo de Investigacion creado");
            response.setData(gruposInvestigacionBL.save(gruposInvestigacionDTO, result));
            appLogger.info("Se creó el Grupo de Investigacion con ID: {}, Nombre: '{}'.",
                    gruposInvestigacionDTO.getGrupoInvestigacionId(), gruposInvestigacionDTO.getNombre());
            return ResponseEntity.ok(response);
        } catch (Exception e){
            LOG.error("Error al crear el Grupo de Investigacion");
            response.setStatus(400);
            response.setMessage("Error al crear el Grupo de Investigacion");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteGruposInvestigacionById(@PathVariable Integer id) {
        ResponseDTO response = new ResponseDTO();
        try{
            LOG.info("Eliminando Grupo de Investigacion con ID: {}", id);
            response.setStatus(200);
            response.setMessage("Grupo de Investigacion eliminado");
            response.setData(gruposInvestigacionBL.deleteGruposInvestigacionById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e){
            LOG.error("Error al eliminar el Grupo de Investigacion");
            response.setStatus(400);
            response.setMessage("Error al eliminar el Grupo de Investigacion");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updateGruposInvestigacion(@Valid @RequestBody GruposInvestigacionDTO gruposInvestigacionDTO, BindingResult result) {
        ResponseDTO response = new ResponseDTO();
        try{
            LOG.info("Actualizando Grupo de Investigacion");
            response.setStatus(200);
            response.setMessage("Grupo de Investigacion actualizado");
            response.setData(gruposInvestigacionBL.updateGruposInvestigacion(gruposInvestigacionDTO, result));
            appLogger.info("Se actualizó el Grupo de Investigacion con ID: {}, Nombre: '{}'.",
                    gruposInvestigacionDTO.getGrupoInvestigacionId(), gruposInvestigacionDTO.getNombre());
            return ResponseEntity.ok(response);
        } catch (Exception e){
            LOG.error("Error al actualizar el Grupo de Investigacion");
            response.setStatus(400);
            response.setMessage("Error al actualizar el Grupo de Investigacion");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
