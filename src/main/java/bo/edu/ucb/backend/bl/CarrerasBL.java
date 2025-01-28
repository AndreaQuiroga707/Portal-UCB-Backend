package bo.edu.ucb.backend.bl;

import bo.edu.ucb.backend.dao.CarrerasDAO;
import bo.edu.ucb.backend.entity.Carreras;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class CarrerasBL {
    @Autowired
    private CarrerasDAO carrerasDAO;
    private static final Logger appLogger = LoggerFactory.getLogger("APP_LOGGER");
    public Carreras findCarreraByNombre(String nombre) {
        return carrerasDAO.findByNombre(nombre);
    }

    public List<Carreras> findCarrerasByFacultadId(Integer facultadId) {
        return carrerasDAO.findByFacultadFacultadId(facultadId);
    }

    public Carreras save(Carreras carreras, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException(result.getFieldErrors().get(0).getDefaultMessage());
        }
        appLogger.info("Guardando nueva carrera: {}", carreras.toString());
        return carrerasDAO.save(carreras);
    }

    public Carreras findCarrerasById(Integer carreraId) {
        return carrerasDAO.findById(carreraId).orElseThrow(() -> new RuntimeException("Carrera no encontrada"));
    }

    public List<Carreras> findAllCarreras() {
        try {
            return carrerasDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar las carreras");
        }
    }

    public Carreras deleteCarrerasById(Integer carreraId) {
        Carreras carreras = findCarrerasById(carreraId);
        if (carreras != null) {
            carrerasDAO.deleteById(carreraId);
        }
        return carreras;
    }

    public Carreras updateCarreras(Carreras carreras, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException(result.getFieldErrors().get(0).getDefaultMessage());
        }
        return carrerasDAO.save(carreras);}
}