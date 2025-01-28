package bo.edu.ucb.backend.bl;

import bo.edu.ucb.backend.dao.InstitutoInvestigacionDAO;
import bo.edu.ucb.backend.dto.InstitutosInvestigacionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class InstitutosInvestigacionBL {
    @Autowired
    InstitutoInvestigacionDAO institutoInvestigacionDAO;

    private static final Logger appLogger = LoggerFactory.getLogger("APP_LOGGER");

    public List<InstitutosInvestigacionDTO> findAllInstitutosInvestigacion() {
        try {
            return institutoInvestigacionDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los institutos de investigación");
        }
    }

    public InstitutosInvestigacionDTO findInstitutoInvestigacionById(Integer id) {
        try {
            Optional<InstitutosInvestigacionDTO> institutosInvestigacionDTO = institutoInvestigacionDAO.findById(id);
            if (institutosInvestigacionDTO.isPresent()) {
                return institutosInvestigacionDTO.get();
            } else {
                throw new RuntimeException("Instituto de investigación no encontrado");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public InstitutosInvestigacionDTO save(InstitutosInvestigacionDTO institutosInvestigacionDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException(result.getFieldErrors().get(0).getDefaultMessage());
        }
        appLogger.info("Guardando instituto de investigación con ID: {}, Nombre: '{}', Enlace web: '{}'.",
                institutosInvestigacionDTO.getInstitutoId(), institutosInvestigacionDTO.getNombre(), institutosInvestigacionDTO.getEnlaceWeb());
        return institutoInvestigacionDAO.save(institutosInvestigacionDTO);
    }

    public InstitutosInvestigacionDTO deleteInstitutoInvestigacionById(Integer id) {
        InstitutosInvestigacionDTO institutosInvestigacionDTO = findInstitutoInvestigacionById(id);
        if (institutosInvestigacionDTO != null) {
            institutoInvestigacionDAO.deleteById(id);
        }
        return institutosInvestigacionDTO;
    }

    public InstitutosInvestigacionDTO updateInstitutoInvestigacion(InstitutosInvestigacionDTO institutosInvestigacionDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException(result.getFieldErrors().get(0).getDefaultMessage());
        }
        return institutoInvestigacionDAO.save(institutosInvestigacionDTO);
    }
}
