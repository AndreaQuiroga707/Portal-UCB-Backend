package bo.edu.ucb.backend.api;
import bo.edu.ucb.backend.bl.ContactosBL;
import bo.edu.ucb.backend.entity.Contactos;
import bo.edu.ucb.backend.dto.ResponseDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/contactos")
public class ContactosAPI {
    private static final Logger LOG = LoggerFactory.getLogger(ContactosAPI.class);

    @Autowired
    private ContactosBL contactosBl;

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> findAllContactos() {
        ResponseDTO response = new ResponseDTO();
        try{
            LOG.info("Obteniendo lista de contactos");
            response.setStatus(200);
            response.setMessage("Lista de contactos");
            response.setData(contactosBl.findAllContactos());
            return ResponseEntity.ok(response);
        } catch (Exception e){
            LOG.error("Error al listar los contactos");
            response.setStatus(400);
            response.setMessage("Error al listar los contactos");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findContactosById(@PathVariable Integer id) {
        ResponseDTO response = new ResponseDTO();
        try{
            LOG.info("Buscando contacto con ID: {}", id);
            response.setStatus(200);
            response.setMessage("Contacto encontrado");
            response.setData(contactosBl.findContactosById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e){
            LOG.error("Error al encontrar el contacto");
            response.setStatus(400);
            response.setMessage("Error al encontrar el contacto");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> saveContactos(@Valid @RequestBody Contactos contactos, BindingResult result) {
        ResponseDTO response = new ResponseDTO();
        try{
            LOG.info("Guardando contacto");
            response.setStatus(200);
            response.setMessage("Contacto guardado");
            response.setData(contactosBl.save(contactos, result));
            return ResponseEntity.ok(response);
        } catch (Exception e){
            LOG.error("Error al guardar el contacto");
            response.setStatus(400);
            response.setMessage("Error al guardar el contacto");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteContactosById(@PathVariable Integer id) {
        ResponseDTO response = new ResponseDTO();
        try{
            LOG.info("Eliminando contacto con ID: {}", id);
            response.setStatus(200);
            response.setMessage("Contacto eliminado");
            response.setData(contactosBl.deleteContactosById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e){
            response.setStatus(400);
            response.setMessage("Error al eliminar el contacto");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updateContacto(@Valid @RequestBody Contactos contactos, BindingResult result) {
        ResponseDTO response = new ResponseDTO();
        try{
            LOG.info("Actualizando contacto");
            response.setStatus(200);
            response.setMessage("Contacto actualizado");
            response.setData(contactosBl.updateContactos(contactos, result));
            return ResponseEntity.ok(response);
        } catch (Exception e){
            LOG.error("Error al actualizar el contacto");
            response.setStatus(400);
            response.setMessage("Error al actualizar el contacto");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
