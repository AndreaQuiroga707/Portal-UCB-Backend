package bo.edu.ucb.backend.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@Entity
@Table(name = "contactos")
public class ContactosDTO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contactoId;

    @NotBlank(message = "El número de cédula es obligatorio")
    private String ci;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Email(message = "El correo electrónico no es válido")
    private String correo;

    private String telefono;

    private String movil;


    public ContactosDTO() {
    }

    public ContactosDTO(Integer contactoId, String ci, String nombre, String correo, String telefono, String movil) {
        this.contactoId = contactoId;
        this.ci = ci;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.movil = movil;
    }

    public Integer getContactoId() {
        return contactoId;
    }

    public void setContactoId(Integer contactoId) {
        this.contactoId = contactoId;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public  void setTelefono(String telefono){
        this.telefono = telefono;
    }

    public String getTelefono(){
        return telefono;
    }

    public  void setMovil(String movil){
        this.movil = movil;
    }

    public String getMovil(){
        return movil;
    }


    @Override
    public String toString() {
        return "ContactosDTO{" +
                "contactoId=" + contactoId +
                ", ci='" + ci + '\'' +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", movil='" + movil + '\'' +
                '}';
    }
}