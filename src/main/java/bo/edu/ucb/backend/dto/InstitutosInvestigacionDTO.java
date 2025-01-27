package bo.edu.ucb.backend.dto;

import bo.edu.ucb.backend.entity.Carreras;
import bo.edu.ucb.backend.entity.Contactos;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "institutos_investigacion")
public class InstitutosInvestigacionDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer institutoId;
    private String enlaceImagen;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    private String enlaceWeb;
    @NotBlank(message ="Las lineas de investigación son obligatorias")
    private String lineasInvestigacion;
    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "carrera_id")
    private Carreras carrera;
    @ManyToOne
    @JoinColumn(name = "contacto_id")
    private Contactos contacto;

    public InstitutosInvestigacionDTO() {
    }

    public InstitutosInvestigacionDTO(Integer institutoId, String nombre, String enlaceWeb, String lineasInvestigacion, String descripcion, Carreras carrera, Contactos contacto) {
        this.institutoId = institutoId;
        this.nombre = nombre;
        this.enlaceWeb = enlaceWeb;
        this.lineasInvestigacion = lineasInvestigacion;
        this.descripcion = descripcion;
        this.carrera = carrera;
        this.contacto = contacto;
    }

    public Integer getInstitutoId() {
        return institutoId;
    }

    public void setInstitutoId(Integer institutoId) {
        this.institutoId = institutoId;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEnlaceWeb() {
        return enlaceWeb;
    }

    public void setEnlaceWeb(String enlaceWeb) {
        this.enlaceWeb = enlaceWeb;
    }

    public String getLineasInvestigacion() {
        return lineasInvestigacion;
    }

    public void setLineasInvestigacion(String lineasInvestigacion) {
        this.lineasInvestigacion = lineasInvestigacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Carreras getCarrera() {
        return carrera;
    }

    public void setCarrera(Carreras carrera) {
        this.carrera = carrera;
    }

    public Contactos getContacto() {
        return contacto;
    }

    public void setContacto(Contactos contacto) {
        this.contacto = contacto;
    }

    @Override
    public String toString() {
        return "InstitutosInvestigacionDTO{" +
                "institutoId=" + institutoId +
                ", nombre='" + nombre + '\'' +
                ", enlaceWeb='" + enlaceWeb + '\'' +
                ", lineasInvestigacion='" + lineasInvestigacion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", carrera=" + carrera +
                ", contacto=" + contacto +
                '}';
    }
}
