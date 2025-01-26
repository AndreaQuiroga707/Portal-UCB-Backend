package bo.edu.ucb.backend.dto;

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
    private CarrerasDTO carrera;
    @ManyToOne
    @JoinColumn(name = "contacto_id")
    private ContactosDTO contacto;

    public SociedadesCientificasDTO() {
    }
    
    public SociedadesCientificasDTO(Integer sociedadId, String nombre, String enlaceWeb, CarrerasDTO carrera, ContactosDTO contacto) {
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
        return "SociedadesCientificasDTO{" + "sociedadId=" + sociedadId + ", enlaceImagen=" + enlaceImagen + ", nombre=" + nombre + ", enlaceWeb=" + enlaceWeb + ", carrera=" + carrera + ", contacto=" + contacto + '}';
    }
}
