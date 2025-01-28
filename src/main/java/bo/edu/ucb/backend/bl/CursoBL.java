package bo.edu.ucb.backend.bl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bo.edu.ucb.backend.dao.CursoDAO;
import bo.edu.ucb.backend.entity.Cursos;
import org.springframework.validation.BindingResult;
import java.util.Optional;

@Service
public class CursoBL {
    private final CursoDAO cursoDAO;

    @Autowired
    public CursoBL(CursoDAO cursoDAO) {
        this.cursoDAO = cursoDAO;
    }
    private static final Logger appLogger = LoggerFactory.getLogger("APP_LOGGER");

    public Cursos saveCurso(Cursos cursos, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        return cursoDAO.save(cursos);
    }

    public Cursos findCursoById(Integer cursoId) {
        Optional<Cursos> cursoOptional = cursoDAO.findById(cursoId);
        return cursoOptional.orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }

    public Iterable<Cursos> findAllCursos() {
        try {
            return cursoDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los cursos");
        }
    }

    public void deleteCursoById(Integer cursoId) {
        if (cursoDAO.existsById(cursoId)) {
            cursoDAO.deleteById(cursoId);
        } else {
            throw new RuntimeException("Curso no encontrado");
        }
    }

    public Cursos updateCurso(Cursos cursos, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        if (cursoDAO.existsById(cursos.getCursoId())) {
            return cursoDAO.save(cursos);
        } else {
            throw new RuntimeException("Curso no encontrado");
        }
    }
}
