package bo.edu.ucb.backend.api;

import bo.edu.ucb.backend.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import bo.edu.ucb.backend.bl.EmailSenderBL;
import bo.edu.ucb.backend.bl.NoticiasBL;
import bo.edu.ucb.backend.dto.EmailDTO;
import bo.edu.ucb.backend.dto.NoticiasDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/noticias")
public class NoticiasAPI {
    private static final Logger LOG = LoggerFactory.getLogger(NoticiasAPI.class);

    @Autowired
    private NoticiasBL noticiasBl;
    @Autowired
    private EmailSenderBL emailSenderBL;

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> findAllNoticias() {
        try {
            LOG.info("Obteniendo lista de noticias"); // Log para obtener la lista de noticias
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Lista de noticias");
            response.setData(noticiasBl.findAllNoticias());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al listar noticias", e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al listar las noticias");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findNoticiasById(@PathVariable Integer id) {
        try {
            LOG.info("Obteniendo noticia por ID: {}", id); // Log para obtener una noticia por ID
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Noticia encontrada");
            response.setData(noticiasBl.findNoticiasById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al encontrar la noticia por ID: {}", id, e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al encontrar la noticia");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> createNoticias(@Valid @RequestBody NoticiasDTO noticiasDTO, BindingResult result) {
        try {
            LOG.info("Creando noticia"); // Log para crear una noticia
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Noticia creada");
            response.setData(noticiasBl.save(noticiasDTO, result));
//            EmailDTO emailDTO = new EmailDTO("andrea.quiroga@ucb.edu.bo", noticiasDTO.getTitulo(), noticiasDTO.getContenido());
            emailSenderBL.sendEmailSuscripcion(noticiasDTO.getTitulo(), noticiasDTO.getContenido());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al crear la noticia", e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al crear la noticia");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteNoticiasById(@PathVariable Integer id) {
        try {
            LOG.info("Eliminando noticia por ID: {}", id); // Log para eliminar una noticia por ID
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Noticia eliminada");
            noticiasBl.deleteNoticiasById(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al eliminar la noticia por ID: {}", id, e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al eliminar la noticia");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updateNoticias(@Valid @RequestBody NoticiasDTO noticiasDTO, BindingResult result) {
        try {
            LOG.info("Actualizando noticia"); // Log para actualizar una noticia
            ResponseDTO response = new ResponseDTO();
            response.setStatus(200);
            response.setMessage("Noticia actualizada");
            response.setData(noticiasBl.updateNoticias(noticiasDTO, result));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error al actualizar la noticia", e); // Log en caso de error
            ResponseDTO response = new ResponseDTO();
            response.setStatus(400);
            response.setMessage("Error al actualizar la noticia");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}