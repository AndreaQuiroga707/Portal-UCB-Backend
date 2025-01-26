package bo.edu.ucb.backend.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bo.edu.ucb.backend.dao.CursoDAO;
import bo.edu.ucb.backend.dto.CursoDTO;
import org.springframework.validation.BindingResult;
import java.util.Optional;

@Service
public class CursoBL {
    private final CursoDAO cursoDAO;

    @Autowired
    public CursoBL(CursoDAO cursoDAO) {
        this.cursoDAO = cursoDAO;
    }

    public CursoDTO saveCurso(CursoDTO cursoDTO, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        return cursoDAO.save(cursoDTO);
    }

    public CursoDTO findCursoById(Integer cursoId) {
        Optional<CursoDTO> cursoOptional = cursoDAO.findById(cursoId);
        return cursoOptional.orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }

    public Iterable<CursoDTO> findAllCursos() {
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

    public CursoDTO updateCurso(CursoDTO cursoDTO, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        if (cursoDAO.existsById(cursoDTO.getCursoId())) {
            return cursoDAO.save(cursoDTO);
        } else {
            throw new RuntimeException("Curso no encontrado");
        }
    }
}
