package bo.edu.ucb.backend.bl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.edu.ucb.backend.dao.DocentesDeCarreraDAO;
import bo.edu.ucb.backend.dto.DocentesDeCarreraDTO;

@Service
public class DocentesDeCarreraBL {
    @Autowired
    private DocentesDeCarreraDAO docentesDeCarreraDAO;

    //no confundan, son solo llaves
    public Iterable<DocentesDeCarreraDTO> findAllDocentesDeCarrera() {
        try {
            return docentesDeCarreraDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los docentes de carrera");
        }
    }

    public DocentesDeCarreraDTO findDocenteDeCarreraById(Integer docenteDeCarreraId) {
        return docentesDeCarreraDAO.findById(docenteDeCarreraId).orElseThrow(() -> new RuntimeException("Docente de carrera no encontrado"));
    }

    public DocentesDeCarreraDTO createDocenteDeCarrera(DocentesDeCarreraDTO docentesDeCarreraDTO) {
        try {
            return docentesDeCarreraDAO.save(docentesDeCarreraDTO);
        } catch (Exception e) {
            throw new RuntimeException("Error al registrar al docente de carrera");
        }
    }

    public DocentesDeCarreraDTO updateDocenteDeCarrera(DocentesDeCarreraDTO docentesDeCarreraDTO) {
        DocentesDeCarreraDTO docenteDeCarreraEncontrado = docentesDeCarreraDAO.findById(docentesDeCarreraDTO.getDocenteDeCarreraId()).orElseThrow(() -> new RuntimeException("Docente de carrera no encontrado"));
        docenteDeCarreraEncontrado.setDocente(docentesDeCarreraDTO.getDocente());
        docenteDeCarreraEncontrado.setCarrera(docentesDeCarreraDTO.getCarrera());
        try {
            return docentesDeCarreraDAO.save(docenteDeCarreraEncontrado);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar al docente de carrera");
        }
    }

    public DocentesDeCarreraDTO deleteDocenteDeCarrera(DocentesDeCarreraDTO docentesDeCarreraDTO) {
        DocentesDeCarreraDTO docenteDeCarreraEncontrado = docentesDeCarreraDAO.findById(docentesDeCarreraDTO.getDocenteDeCarreraId()).orElseThrow(() -> new RuntimeException("Docente de carrera no encontrado"));
        try {
            docentesDeCarreraDAO.delete(docenteDeCarreraEncontrado);
            return docenteDeCarreraEncontrado;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar al docente de carrera");
        }
    }
}
