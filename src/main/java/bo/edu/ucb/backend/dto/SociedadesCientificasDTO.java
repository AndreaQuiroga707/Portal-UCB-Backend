package bo.edu.ucb.backend.dto;

import bo.edu.ucb.backend.entity.Carreras;
import bo.edu.ucb.backend.entity.Contactos;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "sociedades_cientificas")
public class SociedadesCientificasDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sociedadId;
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

    public SociedadesCientificasDTO() {
    }
    
    public SociedadesCientificasDTO(Integer sociedadId, String nombre, String enlaceWeb, Carreras carrera, Contactos contacto) {
        this.sociedadId = sociedadId;
        this.nombre = nombre;
        this.enlaceWeb = enlaceWeb;
        this.carrera = carrera;
        this.contacto = contacto;
    }

    public Integer getSociedadId() {
        return sociedadId;
    }

    public void setSociedadId(Integer sociedadId) {
        this.sociedadId = sociedadId;
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
        return "SociedadesCientificasDTO{" + "sociedadId=" + sociedadId + ", enlaceImagen=" + enlaceImagen + ", nombre=" + nombre + ", enlaceWeb=" + enlaceWeb + ", carrera=" + carrera + ", contacto=" + contacto + '}';
    }
}
