package bo.edu.ucb.backend.bl;

import bo.edu.ucb.backend.dao.SociedadesCientificasDAO;
import bo.edu.ucb.backend.dto.SociedadesCientificasDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

@Service
public class SociedadesCientificasBL {
    @Autowired
    private SociedadesCientificasDAO sociedadesCientificasDAO;
    private static final Logger appLogger = LoggerFactory.getLogger("APP_LOGGER");

    public List<SociedadesCientificasDTO> findAllSociedadesCientificas() {
        try {
            return sociedadesCientificasDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los institutos de investigación");
        }
    }

    public SociedadesCientificasDTO findSociedadesCientificasById(Integer id) {
        try {
            Optional<SociedadesCientificasDTO> sociedadesCientificasDTO = sociedadesCientificasDAO.findById(id);
            if (sociedadesCientificasDTO.isPresent()) {
                return sociedadesCientificasDTO.get();
            } else {
                throw new RuntimeException("Sociedad cientifica no encontrado");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public SociedadesCientificasDTO save(SociedadesCientificasDTO sociedadesCientificasDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException(result.getFieldErrors().get(0).getDefaultMessage());
        }
        appLogger.info("Guardando nueva sociedad científica: {}", sociedadesCientificasDTO.toString());
        return sociedadesCientificasDAO.save(sociedadesCientificasDTO);
    }

    public SociedadesCientificasDTO deleteSociedadesCientificasById(Integer id) {
        SociedadesCientificasDTO sociedadesCientificasDTO = findSociedadesCientificasById(id);
        if (sociedadesCientificasDTO != null) {
            sociedadesCientificasDAO.deleteById(id);
        }
        appLogger.info("Sociedad científica con ID: {} eliminada exitosamente.", id);
        return sociedadesCientificasDTO;
    }

    public SociedadesCientificasDTO updateSociedadesCientificas(SociedadesCientificasDTO sociedadesCientificasDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException(result.getFieldErrors().get(0).getDefaultMessage());
        }
        appLogger.info("Actualizando sociedad científica con ID: {}.", sociedadesCientificasDTO.getSociedadId());
        return sociedadesCientificasDAO.save(sociedadesCientificasDTO);
    }
}
