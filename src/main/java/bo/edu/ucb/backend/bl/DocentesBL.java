package bo.edu.ucb.backend.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.edu.ucb.backend.dao.DocentesDAO;
import bo.edu.ucb.backend.dto.DocentesDTO;
import org.springframework.validation.BindingResult;

@Service
public class DocentesBL {
    @Autowired
    private DocentesDAO docentesDAO;
    
    public DocentesDTO createDocente(DocentesDTO docentesDTO, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        try {
            return docentesDAO.save(docentesDTO);
        } catch (Exception e) {
            throw new RuntimeException("Error al registrar al docente");
        }
    }

    public Iterable<DocentesDTO> findAllDocentes() {
        try {
            return docentesDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los docentes");
        }
    }

    public DocentesDTO findDocenteById(Integer docenteId) {
        return docentesDAO.findById(docenteId).orElseThrow(() -> new RuntimeException("Docente no encontrado"));
    }

    public DocentesDTO updateDocente(DocentesDTO docentesDTO, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        DocentesDTO docenteEncontrado = docentesDAO.findById(docentesDTO.getDocenteId()).orElseThrow(() -> new RuntimeException("Docente no encontrado"));
        docenteEncontrado.setNombre(docentesDTO.getNombre());
        docenteEncontrado.setApellido(docentesDTO.getApellido());
        docenteEncontrado.setTrayectoria(docentesDTO.getTrayectoria());
        docenteEncontrado.setFoto(docentesDTO.getFoto());
        try {
            return docentesDAO.save(docenteEncontrado);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar al docente");
        }
    }

    public DocentesDTO deleteDocente(DocentesDTO docentesDTO) {
        DocentesDTO docenteEncontrado = docentesDAO.findById(docentesDTO.getDocenteId()).orElseThrow(() -> new RuntimeException("Docente no encontrado"));
        try {
            docentesDAO.delete(docenteEncontrado);
            return docenteEncontrado;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar al docente");
        }
    }
}