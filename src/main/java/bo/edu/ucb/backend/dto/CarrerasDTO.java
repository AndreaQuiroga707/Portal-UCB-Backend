package bo.edu.ucb.backend.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "carreras")
public class CarrerasDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carreraId;

    @NotBlank(message = "El nombre de la carrera es obligatorio")
    private String nombre;

    @NotBlank(message = "Las siglas de la carrera son obligatorias")
    private String siglas;

    @NotBlank(message = "Las acreditaciones de la carrera son obligatorias")
    private String acreditacion;
    
    @NotNull(message = "La duración de la carrera debe ser mayor que cero")
    @Positive(message = "La duración de la carrera debe ser mayor que cero")
    private Integer duracionSemestres;

    @NotBlank(message = "Los areas de estudio de la carrera son obligatorias")
    private String areasEstudio;

    @NotBlank(message = "Las modalidades de graduación de la carrera son obligatorias")
    private String modalidadesGraduacion;

    @NotBlank(message = "Los beneficios de la carrera son obligatorios")
    private String beneficios;

    @NotBlank(message = "El enlace a la malla curricular de la carrera es obligatorio")
    private String mallaCurricularPdf;

    @NotBlank(message = "El enlace al video promocional de la carrera es obligatorio")
    private String videoPromocional;

    @NotBlank(message = "Los posibles lugares de trabajo de la carrera son obligatorios")
    private String dondeTrabajar;

    //CAMBIOS??
    @NotBlank(message = "El enlace a los horarios de los docentes de la carrera es obligatorio")
    String docentesTiempoHorario;
    @ManyToOne
    @JoinColumn(name = "facultad_id")
    private FacultadesDTO facultad;
    @ManyToOne
    @JoinColumn(name = "contacto_id")
    private ContactosDTO contacto;

    public CarrerasDTO() {
    }

    public CarrerasDTO(Integer carreraId, String nombre, String siglas, String acreditacion, Integer duracionSemestres, String areasEstudio, String modalidadesGraduacion, String beneficios, String mallaCurricularPdf, String videoPromocional, String dondeTrabajar, String docentesTiempoHorario, FacultadesDTO facultad, ContactosDTO contacto) {
        this.carreraId = carreraId;
        this.nombre = nombre;
        this.siglas = siglas;
        this.acreditacion = acreditacion;
        this.duracionSemestres = duracionSemestres;
        this.areasEstudio = areasEstudio;
        this.modalidadesGraduacion = modalidadesGraduacion;
        this.beneficios = beneficios;
        this.mallaCurricularPdf = mallaCurricularPdf;
        this.videoPromocional = videoPromocional;
        this.dondeTrabajar = dondeTrabajar;
        this.docentesTiempoHorario = docentesTiempoHorario;
        this.facultad = facultad;
        this.contacto = contacto;
    }

    public Integer getCarreraId() {
        return carreraId;
    }

    public void setCarreraId(Integer carreraId) {
        this.carreraId = carreraId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombreCarrera) {
        this.nombre = nombreCarrera;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglasCarrera) {
        this.siglas = siglasCarrera;
    }

    public String getAcreditacion() {
        return acreditacion;
    }

    public void setAcreditacion(String acreditacionCarrera) {
        this.acreditacion = acreditacionCarrera;
    }

    public Integer getduracionSemestres() {
        return duracionSemestres;
    }

    public void setduracionSemestres(Integer duracionSemestres) {
        this.duracionSemestres = duracionSemestres;
    }

    public String getAreasEstudio() {
        return areasEstudio;
    }

    public void setAreasEstudio(String areasEstudio) {
        this.areasEstudio = areasEstudio;
    }

    public String getModalidadesGraduacion() {
        return modalidadesGraduacion;
    }

    public void setModalidadesGraduacion(String modalidadesGraduacion) {
        this.modalidadesGraduacion = modalidadesGraduacion;
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficiosCarrera) {
        this.beneficios = beneficiosCarrera;
    }

    public String getMallaCurricularPdf() {
        return mallaCurricularPdf;
    }

    public void setMallaCurricularPdf(String mallaCurricularPdf) {
        this.mallaCurricularPdf = mallaCurricularPdf;
    }

    public String getVideoPromocional() {
        return videoPromocional;
    }

    public void setVideoPromocional(String videoPromocional) {
        this.videoPromocional = videoPromocional;
    }

    public String getDondeTrabajar() {
        return dondeTrabajar;
    }

    public void setDondeTrabajar(String dondeTrabajar) {
        this.dondeTrabajar = dondeTrabajar;
    }

    public String getDocentesTiempoHorario() {
        return docentesTiempoHorario;
    }

    public void setDocentesTiempoHorario(String docentesTiempoHorario) {
        this.docentesTiempoHorario = docentesTiempoHorario;
    }

    public FacultadesDTO getFacultad() {
        return facultad;
    }

    public void setFacultad(FacultadesDTO facultad) {
        this.facultad = facultad;
    }

    public ContactosDTO getContacto() {
        return contacto;
    }

    public void setContacto(ContactosDTO contacto) {
        this.contacto = contacto;
    }

    @Override
    public String toString() {
        return "CarrerasDTO{" +
                "carreraId=" + carreraId +
                ", nombreCarrera='" + nombre + '\'' +
                ", siglasCarrera='" + siglas + '\'' +
                ", acreditacionCarrera='" + acreditacion + '\'' +
                ", duracionSemestres=" + duracionSemestres +
                ", areasEstudio='" + areasEstudio + '\'' +
                ", modalidadesGraduacion='" + modalidadesGraduacion + '\'' +
                ", beneficiosCarrera='" + beneficios + '\'' +
                ", mallaCurricularPdf='" + mallaCurricularPdf + '\'' +
                ", videoPromocional='" + videoPromocional + '\'' +
                ", dondeTrabajar='" + dondeTrabajar + '\'' +
                ", docentesTiempoHorario='" + docentesTiempoHorario + '\'' +
                ", facultad=" + facultad +
                ", contacto=" + contacto +
                '}';
    }
}
