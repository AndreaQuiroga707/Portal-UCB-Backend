package bo.edu.ucb.backend.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "docentes_de_carrera")
public class DocentesDeCarreraDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer docenteDeCarreraId;
    
    @ManyToOne
    @JoinColumn(name = "docente_id")
    private DocentesDTO docente;

    @ManyToOne
    @JoinColumn(name = "carrera_id")
    private CarrerasDTO carrera;
    
    public DocentesDeCarreraDTO() {
    }

    public DocentesDeCarreraDTO(Integer docenteDeCarreraId, DocentesDTO docente, CarrerasDTO carrera) {
        this.docenteDeCarreraId = docenteDeCarreraId;
        this.docente = docente;
        this.carrera = carrera;
    }

    public Integer getDocenteDeCarreraId() {
        return docenteDeCarreraId;
    }

    public void setDocenteDeCarreraId(Integer docenteDeCarreraId) {
        this.docenteDeCarreraId = docenteDeCarreraId;
    }

    public DocentesDTO getDocente() {
        return docente;
    }

    public void setDocente(DocentesDTO docente) {
        this.docente = docente;
    }

    public CarrerasDTO getCarrera() {
        return carrera;
    }

    public void setCarrera(CarrerasDTO carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "DocentesDeCarreraDTO [carrera=" + carrera + ", docente=" + docente + ", docenteDeCarreraId="
                + docenteDeCarreraId + "]";
    }
}
