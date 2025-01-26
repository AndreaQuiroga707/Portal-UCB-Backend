package bo.edu.ucb.backend.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "facultades")
public class FacultadesDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facultad_id")
    private Integer facultadId;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    public FacultadesDTO() {
    }

    public FacultadesDTO(Integer facultadId, String nombre, String descripcion) {
        this.facultadId = facultadId;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getFacultadId() {
        return facultadId;
    }

    public void setFacultadId(Integer facultadId) {
        this.facultadId = facultadId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}