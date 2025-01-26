package bo.edu.ucb.backend.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "centros_investigacion")
public class CentrosInvestigacionDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer centroId;
    private String enlaceImagen;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    private String enlaceWeb;
    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "carrera_id")
    private CarrerasDTO carrera;
    @ManyToOne
    @JoinColumn(name = "contacto_id")
    private ContactosDTO contacto;

    public CentrosInvestigacionDTO() {
    }

    public CentrosInvestigacionDTO(Integer centroId, String enlaceImagen, String nombreCentroInvestigacion, String enlaceWeb, String descripcionCentroInvestigacion, CarrerasDTO carrera, ContactosDTO contacto) {
        this.centroId = centroId;
        this.enlaceImagen = enlaceImagen;
        this.nombre = nombreCentroInvestigacion;
        this.enlaceWeb = enlaceWeb;
        this.descripcion = descripcionCentroInvestigacion;
        this.carrera = carrera;
        this.contacto = contacto;
    }

    public Integer getIdCentroInvestigacion() {
        return centroId;
    }

    public void setIdCentroInvestigacion(Integer idCentroInvestigacion) {
        this.centroId = idCentroInvestigacion;
    }

    public String getEnlaceImagen() {
        return enlaceImagen;
    }

    public void setEnlaceImagen(String enlaceImagen) {
        this.enlaceImagen = enlaceImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombreCentroInvestigacion) {
        this.nombre = nombreCentroInvestigacion;
    }

    public String getEnlaceWeb() {
        return enlaceWeb;
    }

    public void setEnlaceWeb(String enlaceWeb) {
        this.enlaceWeb = enlaceWeb;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcionCentroInvestigacion) {
        this.descripcion = descripcionCentroInvestigacion;
    }

    public CarrerasDTO getCarrera() {
        return carrera;
    }

    public void setCarrera(CarrerasDTO carrera) {
        this.carrera = carrera;
    }

    public ContactosDTO getContacto() {
        return contacto;
    }

    public void setContacto(ContactosDTO contacto) {
        this.contacto = contacto;
    }

    @Override
    public String toString() {
        return "CentrosInvestigacionDTO{" +
                "idCentroInvestigacion=" + centroId +
                ", enlaceImagen='" + enlaceImagen + '\'' +
                ", nombreCentroInvestigacion='" + nombre + '\'' +
                ", enlaceWeb='" + enlaceWeb + '\'' +
                ", descripcionCentroInvestigacion='" + descripcion + '\'' +
                ", carrera=" + carrera +
                ", contacto=" + contacto +
                '}';
    }
}
