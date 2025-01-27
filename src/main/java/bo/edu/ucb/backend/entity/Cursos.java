package bo.edu.ucb.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cursos")
public class Cursos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curso_id")
    private Integer cursoId;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "programa_id")
    private Integer programaId;

    @Column(name = "descripcion", columnDefinition = "text")
    private String descripcion;

    public Cursos() {
    }

    public Cursos(Integer cursoId, String nombre, Integer programaId, String descripcion) {
        this.cursoId = cursoId;
        this.nombre = nombre;
        this.programaId = programaId;
        this.descripcion = descripcion;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getProgramaId() {
        return programaId;
    }

    public void setProgramaId(Integer programaId) {
        this.programaId = programaId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
