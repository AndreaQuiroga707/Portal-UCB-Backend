package bo.edu.ucb.backend.api;

import bo.edu.ucb.backend.bl.SociedadesCientificasBL;
import bo.edu.ucb.backend.dto.ResponseDTO;
import bo.edu.ucb.backend.dto.SociedadesCientificasDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Angular
@RequestMapping("/api/v1/sociedades/cientificas")
public class SociedadesCientificasAPI {
    private static final Logger LOG = LoggerFactory.getLogger(SociedadesCientificasAPI.class);
    private static final Logger appLogger = LoggerFactory.getLogger("APP_LOGGER");
    @Autowired
    private SociedadesCientificasBL sociedadesCientificasBL;
    @GetMapping("/")
    public ResponseEntity<ResponseDTO> findAllSociedadesCientificas() {
        ResponseDTO response = new ResponseDTO();
        try{
            LOG.info("Obteniendo lista de Sociedades Cientificas");
            response.setStatus(200);
            response.setMessage("Lista de Sociedades Cientificas");
            response.setData(sociedadesCientificasBL.findAllSociedadesCientificas());
            return ResponseEntity.ok(response);
        } catch (Exception e){
            LOG.error("Error al listar las Sociedades Cientificas");
            response.setStatus(400);
            response.setMessage("Error al listar las Sociedades Cientificas");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findSociedadCientificaById(@PathVariable Integer id) {
        ResponseDTO response = new ResponseDTO();
        try{
            LOG.info("Buscando Sociedad Cientifica con ID: {}", id);
            response.setStatus(200);
            response.setMessage("Sociedad Cientifica encontrada");
            response.setData(sociedadesCientificasBL.findSociedadesCientificasById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e){
            LOG.error("Error al encontrar la Sociedad Cientifica");
            response.setStatus(400);
            response.setMessage("Error al encontrar la Sociedad Cientifica");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> createSociedadCientifica(@Valid @RequestBody SociedadesCientificasDTO sociedadesCientificasDTO, BindingResult result) {
        ResponseDTO response = new ResponseDTO();
        try{
            LOG.info("Creando Sociedad Cientifica");
            response.setStatus(200);
            response.setMessage("Sociedad Cientifica creada");
            response.setData(sociedadesCientificasBL.save(sociedadesCientificasDTO, result));
            appLogger.info("Se creó la Sociedad Cientifica con ID: {}, Nombre: '{}'.",
                    sociedadesCientificasDTO.getSociedadId(), sociedadesCientificasDTO.getNombre());
            return ResponseEntity.ok(response);
        } catch (Exception e){
            LOG.error("Error al crear la Sociedad Cientifica");
            response.setStatus(400);
            response.setMessage("Error al crear la Sociedad Cientifica");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteSociedadCientificaById(@PathVariable Integer id) {
        ResponseDTO response = new ResponseDTO();
        try{
            LOG.info("Eliminando Sociedad Cientifica con ID: {}", id);
            response.setStatus(200);
            response.setMessage("Sociedad Cientifica eliminada");
            response.setData(sociedadesCientificasBL.deleteSociedadesCientificasById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e){
            LOG.error("Error al eliminar la Sociedad Cientifica");
            response.setStatus(400);
            response.setMessage("Error al eliminar la Sociedad Cientifica");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updateSociedadCientifica(@Valid @RequestBody SociedadesCientificasDTO sociedadesCientificasDTO, BindingResult result) {
        ResponseDTO response = new ResponseDTO();
        try{
            LOG.info("Actualizando Sociedad Cientifica");
            response.setStatus(200);
            response.setMessage("Sociedad Cientifica actualizada");
            response.setData(sociedadesCientificasBL.updateSociedadesCientificas(sociedadesCientificasDTO, result));
            appLogger.info("Se actualizó la Sociedad Cientifica con ID: {}, Nombre: '{}'.",
                    sociedadesCientificasDTO.getSociedadId(), sociedadesCientificasDTO.getNombre());
            return ResponseEntity.ok(response);
        } catch (Exception e){
            LOG.error("Error al actualizar la Sociedad Cientifica");
            response.setStatus(400);
            response.setMessage("Error al actualizar la Sociedad Cientifica");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
