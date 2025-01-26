package bo.edu.ucb.backend.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "programas_academicos")
public class ProgramaAcademicoDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "programa_id")
    private Integer programaId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "facultad_id")
    private Integer facultadId;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    public ProgramaAcademicoDTO() {
    }

    public ProgramaAcademicoDTO(Integer programaId, String nombre, Integer facultadId, String descripcion) {
        this.programaId = programaId;
        this.nombre = nombre;
        this.facultadId = facultadId;
        this.descripcion = descripcion;
    }

    public Integer getProgramaId() {
        return programaId;
    }

    public void setProgramaId(Integer programaId) {
        this.programaId = programaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFacultadId() {
        return facultadId;
    }

    public void setFacultadId(Integer facultadId) {
        this.facultadId = facultadId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}