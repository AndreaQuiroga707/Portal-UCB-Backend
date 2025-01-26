package bo.edu.ucb.backend.dto;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "eventos")
public class EventosDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventoId;
    private String enlaceImagen;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;
    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDate fechaFin;
    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;

    public EventosDTO() {
    }

    public EventosDTO(Integer eventoId, String enlaceImagen, String nombre, LocalDate fechaInicio, LocalDate fechaFin, String descripcion) {
        this.eventoId = eventoId;
        this.enlaceImagen = enlaceImagen;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcion = descripcion;
    }

    public Integer getEventoId() {
        return eventoId;
    }

    public void setEventoId(Integer eventoId) {
        this.eventoId = eventoId;
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString(){
        return "EventosDTO{" +
                "eventoId=" + eventoId +
                ", enlaceImagen='" + enlaceImagen + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", fechaFin='" + fechaFin + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
