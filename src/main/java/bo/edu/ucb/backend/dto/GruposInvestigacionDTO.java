package bo.edu.ucb.backend.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "grupos_investigacion")
public class GruposInvestigacionDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer grupoId;
    private String enlaceImagen;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    private String enlaceWeb;
    @ManyToOne
    @JoinColumn(name = "carrera_id")
    private CarrerasDTO carrera;
    @ManyToOne
    @JoinColumn(name = "contacto_id")
    private ContactosDTO contacto;

    public GruposInvestigacionDTO() {
    }

    public GruposInvestigacionDTO(Integer grupoId, String enlaceImagen, @NotBlank(message = "El nombre es obligatorio") String nombre, String enlaceWeb, CarrerasDTO carrera, ContactosDTO contacto) {
        this.grupoId = grupoId;
        this.enlaceImagen = enlaceImagen;
        this.nombre = nombre;
        this.enlaceWeb = enlaceWeb;
        this.carrera = carrera;
        this.contacto = contacto;
    }

    public Integer getGrupoInvestigacionId() {
        return grupoId;
    }

    public void setGrupoInvestigacionId(Integer grupoInvestigacionId) {
        this.grupoId = grupoInvestigacionId;
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
        return "GruposInvestigacionDTO{" +
                "grupoInvestigacionId=" + grupoId +
                ", enlaceImagen='" + enlaceImagen + '\'' +
                ", nombre='" + nombre + '\'' +
                ", enlaceWeb='" + enlaceWeb + '\'' +
                ", carrera=" + carrera +
                ", contacto=" + contacto +
                '}';
    }
}
