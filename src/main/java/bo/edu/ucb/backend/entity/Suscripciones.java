package bo.edu.ucb.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "suscripciones")
public class Suscripciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer suscripcionId;

    @Email(message = "El correo no es valido")
    private String correo;

    public Suscripciones() {
    }

    public Suscripciones(Integer suscripcionId, String correo) {
        this.suscripcionId = suscripcionId;
        this.correo = correo;
    }

    public Integer getSuscripcionId() {
        return suscripcionId;
    }

    public void setSuscripcionId(Integer suscripcionId) {
        this.suscripcionId = suscripcionId;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "SuscripcionesDTO{" +
                "suscripcionId=" + suscripcionId +
                ", correo='" + correo + '\'' +
                '}';
    }
}
