package bo.edu.ucb.backend.bl;

import bo.edu.ucb.backend.dao.GruposInvestigacionDAO;
import bo.edu.ucb.backend.dto.GruposInvestigacionDTO;
import bo.edu.ucb.backend.dto.SociedadesCientificasDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class GruposInvestigacionBL {
    @Autowired
    GruposInvestigacionDAO gruposInvestigacionDAO;
    private static final Logger appLogger = LoggerFactory.getLogger("APP_LOGGER");

    public List<GruposInvestigacionDTO> findAllGruposInvestigacion() {
        try {
            return gruposInvestigacionDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los Grupos de investigación");
        }
    }

    public GruposInvestigacionDTO findGruposInvestigacionById(Integer id) {
        try {
            Optional<GruposInvestigacionDTO> gruposInvestigacionDTO = gruposInvestigacionDAO.findById(id);
            if (gruposInvestigacionDTO.isPresent()) {
                return gruposInvestigacionDTO.get();
            } else {
                throw new RuntimeException("Grupo de investigación no encontrado");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public GruposInvestigacionDTO save(GruposInvestigacionDTO gruposInvestigacionDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException(result.getFieldErrors().get(0).getDefaultMessage());
        }
        appLogger.info("Guardando grupo de investigación con ID: {}, Nombre: '{}', Enlace web: '{}', Carrera: '{}'.",
                gruposInvestigacionDTO.getGrupoInvestigacionId(), gruposInvestigacionDTO.getNombre(), gruposInvestigacionDTO.getEnlaceWeb(), gruposInvestigacionDTO.getCarrera().getNombre());
        return gruposInvestigacionDAO.save(gruposInvestigacionDTO);
    }

    public GruposInvestigacionDTO deleteGruposInvestigacionById(Integer id) {
        GruposInvestigacionDTO gruposInvestigacionDTO = findGruposInvestigacionById(id);
        if (gruposInvestigacionDTO != null) {
            gruposInvestigacionDAO.deleteById(id);
        }
        return gruposInvestigacionDTO;
    }

    public GruposInvestigacionDTO updateGruposInvestigacion(GruposInvestigacionDTO gruposInvestigacionDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException(result.getFieldErrors().get(0).getDefaultMessage());
        }
        return gruposInvestigacionDAO.save(gruposInvestigacionDTO);
    }
}
