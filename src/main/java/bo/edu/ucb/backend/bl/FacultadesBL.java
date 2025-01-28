package bo.edu.ucb.backend.bl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bo.edu.ucb.backend.dao.FacultadesDAO;
import bo.edu.ucb.backend.dto.FacultadesDTO;
import org.springframework.validation.BindingResult;

@Service
public class FacultadesBL {
    @Autowired
    private FacultadesDAO facultadesDAO;


    public FacultadesDTO save(FacultadesDTO facultadesDTO, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }

        return facultadesDAO.save(facultadesDTO);
    }

    public FacultadesDTO findFacultadesById(Integer facultadId) {
        return facultadesDAO.findById(facultadId).orElseThrow(() -> new RuntimeException("Facultad no encontrada"));
    }

    public Iterable<FacultadesDTO> findAllFacultades() {
        try {
            return facultadesDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar las facultades");
        }
    }

    public void deleteFacultadesById(Integer facultadId) {
        if (facultadesDAO.findById(facultadId).isPresent()) {
            facultadesDAO.deleteById(facultadId);
        } else {
            throw new RuntimeException("Facultad no encontrada");
        }
    }

    public FacultadesDTO updateFacultades(FacultadesDTO facultadesDTO, BindingResult result){
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        if (facultadesDAO.findById(facultadesDTO.getFacultadId()).isPresent()) {
            return facultadesDAO.save(facultadesDTO);
        } else {
            throw new RuntimeException("Facultad no encontrada");
        }
    }
}