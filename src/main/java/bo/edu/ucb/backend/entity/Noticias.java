package bo.edu.ucb.backend.entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "noticias")
public class Noticias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noticiaId;
    private String enlaceImagen;
    @NotBlank(message = "El titulo es obligatorio")
    private String titulo;
    @NotBlank(message = "El contenido es obligatorio")
    private String contenido;
    @NotNull(message = "La fecha de publicacion es obligatoria")
    private LocalDate fechaPublicacion;

    public Noticias() {
    }

    public Noticias(Integer noticiaId, String enlaceImagen, String titulo, String contenido, LocalDate fechaPublicacion) {
        this.noticiaId = noticiaId;
        this.enlaceImagen = enlaceImagen;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaPublicacion = fechaPublicacion;
    }

    public Integer getNoticiaId() {
        return noticiaId;
    }

    public void setNoticiaId(Integer noticiaId) {
        this.noticiaId = noticiaId;
    }

    public String getEnlaceImagen() {
        return enlaceImagen;
    }

    public void setEnlaceImagen(String enlaceImagen) {
        this.enlaceImagen = enlaceImagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    @Override
    public String toString() {
        return "NoticiasDTO{" + "noticiaId=" + noticiaId + ", enlaceImagen=" + enlaceImagen + ", titulo=" + titulo + ", contenido=" + contenido + ", fechaPublicacion=" + fechaPublicacion + '}';
    }
}
