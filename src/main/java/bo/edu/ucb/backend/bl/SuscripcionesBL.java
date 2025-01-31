package bo.edu.ucb.backend.bl;

import bo.edu.ucb.backend.dao.SuscripcionesDAO;
import bo.edu.ucb.backend.entity.Suscripciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuscripcionesBL {
    @Autowired
    private SuscripcionesDAO suscripcionesDAO;
    private static final Logger appLogger = LoggerFactory.getLogger("APP_LOGGER");
    public Iterable<Suscripciones> findAllSuscripciones() {
        try {
            return suscripcionesDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar las suscripciones");
        }
    }

    public List<Suscripciones> findAllSuscripcionesList() {
        try {
            return suscripcionesDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar las suscripciones");
        }
    }

    public Suscripciones save(Suscripciones suscripciones) {
        appLogger.info("Suscripción guardada exitosamente con ID: {}.", suscripciones.getSuscripcionId());
        return suscripcionesDAO.save(suscripciones);
    }

    public Suscripciones deleteSuscripcionesById(Integer suscripcionId) {
        if (suscripcionesDAO.findById(suscripcionId).isPresent()) {
            Suscripciones suscripciones = suscripcionesDAO.findById(suscripcionId).get();
            suscripcionesDAO.deleteById(suscripcionId);
            appLogger.info("Suscripción eliminada exitosamente con ID: {} y correo: '{}'.",
                    suscripciones.getSuscripcionId(), suscripciones.getCorreo());
            return suscripciones;
        } else {
            throw new RuntimeException("Suscripcion no encontrada");
        }
    }

    public Suscripciones deleteSuscripcionesByEmail(String correo) {
        try {
            suscripcionesDAO.deleteByCorreo(correo);
            appLogger.info("Suscripción eliminada exitosamente con el correo: '{}'.",correo);
            return new Suscripciones();
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la suscripción");
        }
    }
}
