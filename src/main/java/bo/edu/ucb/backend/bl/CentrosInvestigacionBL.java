package bo.edu.ucb.backend.bl;

import bo.edu.ucb.backend.dao.CentrosInvestigacionDAO;
import bo.edu.ucb.backend.dto.CentrosInvestigacionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class CentrosInvestigacionBL {
    @Autowired
    private CentrosInvestigacionDAO centrosInvestigacionDAO;

    public List<CentrosInvestigacionDTO> findAllInstitutosInvestigacion() {
        try {
            return centrosInvestigacionDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los institutos de investigación");
        }
    }

    public CentrosInvestigacionDTO findInstitutoInvestigacionById(Integer id) {
        try {
            Optional<CentrosInvestigacionDTO> centrosInvestigacionDTO = centrosInvestigacionDAO.findById(id);
            if (centrosInvestigacionDTO.isPresent()) {
                return centrosInvestigacionDTO.get();
            } else {
                throw new RuntimeException("Instituto de investigación no encontrado");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public CentrosInvestigacionDTO save(CentrosInvestigacionDTO centrosInvestigacionDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException(result.getFieldErrors().get(0).getDefaultMessage());
        }
        return centrosInvestigacionDAO.save(centrosInvestigacionDTO);
    }

    public CentrosInvestigacionDTO deleteInstitutoInvestigacionById(Integer id) {
        CentrosInvestigacionDTO centrosInvestigacionDTO = findInstitutoInvestigacionById(id);
        if (centrosInvestigacionDTO != null) {
            centrosInvestigacionDAO.deleteById(id);
        }
        return centrosInvestigacionDTO;
    }

    public CentrosInvestigacionDTO updateInstitutoInvestigacion(CentrosInvestigacionDTO centrosInvestigacionDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException(result.getFieldErrors().get(0).getDefaultMessage());
        }
        return centrosInvestigacionDAO.save(centrosInvestigacionDTO);
    }
}