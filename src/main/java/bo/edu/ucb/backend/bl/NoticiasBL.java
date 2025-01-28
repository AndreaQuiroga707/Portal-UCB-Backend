package bo.edu.ucb.backend.bl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.edu.ucb.backend.dao.NoticiasDAO;
import bo.edu.ucb.backend.entity.Noticias;
import org.springframework.validation.BindingResult;

@Service
public class NoticiasBL {
    @Autowired
    private NoticiasDAO noticiasDAO;

    public Noticias save(Noticias noticias, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        return noticiasDAO.save(noticias);
    }
    public Noticias findNoticiasById(Integer noticiaId) {
        return noticiasDAO.findById(noticiaId).orElseThrow(() -> new RuntimeException("Noticia no encontrada"));
    }

    public Iterable<Noticias> findAllNoticias() {
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

    public Noticias updateNoticias(Noticias noticias, BindingResult result){
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        if (noticiasDAO.findById(noticias.getNoticiaId()).isPresent()) {
            return noticiasDAO.save(noticias);
        } else {
            throw new RuntimeException("Noticia no encontrada");
        }
    }
}
