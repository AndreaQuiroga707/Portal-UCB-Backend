package bo.edu.ucb.backend.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.edu.ucb.backend.dao.NoticiasDAO;
import bo.edu.ucb.backend.dto.NoticiasDTO;
import org.springframework.validation.BindingResult;

@Service
public class NoticiasBL {
    @Autowired
    private NoticiasDAO noticiasDAO;

    public NoticiasDTO save(NoticiasDTO noticiasDTO, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        return noticiasDAO.save(noticiasDTO);
    }
    public NoticiasDTO findNoticiasById(Integer noticiaId) {
        return noticiasDAO.findById(noticiaId).orElseThrow(() -> new RuntimeException("Noticia no encontrada"));
    }

    public Iterable<NoticiasDTO> findAllNoticias() {
        try {
            return noticiasDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar las noticias");
        }
    }

    public void deleteNoticiasById(Integer noticiaId) {
        if (noticiasDAO.findById(noticiaId).isPresent()) {
            noticiasDAO.deleteById(noticiaId);
        } else {
            throw new RuntimeException("Noticia no encontrada");
        }
    }

    public NoticiasDTO updateNoticias(NoticiasDTO noticiasDTO, BindingResult result){
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        if (noticiasDAO.findById(noticiasDTO.getNoticiaId()).isPresent()) {
            return noticiasDAO.save(noticiasDTO);
        } else {
            throw new RuntimeException("Noticia no encontrada");
        }
    }
}
