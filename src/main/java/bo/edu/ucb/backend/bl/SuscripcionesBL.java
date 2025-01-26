package bo.edu.ucb.backend.bl;

import bo.edu.ucb.backend.dao.SuscripcionesDAO;
import bo.edu.ucb.backend.dto.SuscripcionesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuscripcionesBL {
    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(SuscripcionesBL.class);
    @Autowired
    private SuscripcionesDAO suscripcionesDAO;

    public Iterable<SuscripcionesDTO> findAllSuscripciones() {
        try {
            return suscripcionesDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar las suscripciones");
        }
    }

    public List<SuscripcionesDTO> findAllSuscripcionesList() {
        try {
            return suscripcionesDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar las suscripciones");
        }
    }

    public SuscripcionesDTO save(SuscripcionesDTO suscripcionesDTO) {
        return suscripcionesDAO.save(suscripcionesDTO);
    }

    public SuscripcionesDTO deleteSuscripcionesById(Integer suscripcionId) {
        if (suscripcionesDAO.findById(suscripcionId).isPresent()) {
            SuscripcionesDTO suscripcionesDTO = suscripcionesDAO.findById(suscripcionId).get();
            suscripcionesDAO.deleteById(suscripcionId);
            return suscripcionesDTO;
        } else {
            throw new RuntimeException("Suscripcion no encontrada");
        }
    }

    public SuscripcionesDTO deleteSuscripcionesByEmail(String correo) {
        try {
            suscripcionesDAO.deleteByCorreo(correo);
            return new SuscripcionesDTO();
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la suscripción");
        }
    }
}
