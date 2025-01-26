package bo.edu.ucb.backend.bl;

import bo.edu.ucb.backend.dao.CarrerasDAO;
import bo.edu.ucb.backend.dto.CarrerasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class CarrerasBL {
    @Autowired
    private CarrerasDAO carrerasDAO;

    public CarrerasDTO findCarreraByNombre(String nombre) {
        return carrerasDAO.findByNombre(nombre);
    }

    public List<CarrerasDTO> findCarrerasByFacultadId(Integer facultadId) {
        return carrerasDAO.findByFacultadFacultadId(facultadId);
    }

    public CarrerasDTO save(CarrerasDTO carrerasDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException(result.getFieldErrors().get(0).getDefaultMessage());
        }
        return carrerasDAO.save(carrerasDTO);
    }

    public CarrerasDTO findCarrerasById(Integer carreraId) {
        return carrerasDAO.findById(carreraId).orElseThrow(() -> new RuntimeException("Carrera no encontrada"));
    }

    public List<CarrerasDTO> findAllCarreras() {
        try {
            return carrerasDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar las carreras");
        }
    }

    public CarrerasDTO deleteCarrerasById(Integer carreraId) {
        CarrerasDTO carrerasDTO = findCarrerasById(carreraId);
        if (carrerasDTO != null) {
            carrerasDAO.deleteById(carreraId);
        }
        return carrerasDTO;
    }

    public CarrerasDTO updateCarreras(CarrerasDTO carrerasDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException(result.getFieldErrors().get(0).getDefaultMessage());
        }
        return carrerasDAO.save(carrerasDTO);}
}