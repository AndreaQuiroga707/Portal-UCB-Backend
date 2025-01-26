package bo.edu.ucb.backend.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "docentes")
public class DocentesDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer docenteId;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "La trayectoria profesional es obligatoria")
    private String trayectoria;

    @NotBlank(message = "La foto es obligatoria")
    private String foto;

    

    // Constructores, getters y setters
    
    public DocentesDTO() {
    }

    public DocentesDTO(Integer docenteId, String nombre, String apellido, String trayectoria, String foto) {
        this.docenteId = docenteId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.trayectoria = trayectoria;
        this.foto = foto;
    }

    public Integer getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(Integer docenteId) {
        this.docenteId = docenteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }    

    public String getTrayectoria() {
        return trayectoria;
    }

    public void setTrayectoria(String trayectoria) {
        this.trayectoria = trayectoria;
    }

    public String getFoto(){
        return foto;
    }

    public void setFoto(String foto){
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "DocentesDTO{" +
                "docenteId=" + docenteId +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", trayectoria='" + trayectoria + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }
}