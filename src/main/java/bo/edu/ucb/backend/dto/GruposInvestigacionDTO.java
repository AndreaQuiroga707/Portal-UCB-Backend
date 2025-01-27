package bo.edu.ucb.backend.dto;

import bo.edu.ucb.backend.entity.Carreras;
import bo.edu.ucb.backend.entity.Contactos;
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
    private Carreras carrera;
    @ManyToOne
    @JoinColumn(name = "contacto_id")
    private Contactos contacto;

    public GruposInvestigacionDTO() {
    }

    public GruposInvestigacionDTO(Integer grupoId, String enlaceImagen, @NotBlank(message = "El nombre es obligatorio") String nombre, String enlaceWeb, Carreras carrera, Contactos contacto) {
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
