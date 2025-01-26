package bo.edu.ucb.backend.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "usuarios")
public class UsuarioDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuarioId;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Email
    @NotBlank(message = "El correo electrónico es obligatorio")
    private String correoElectronico;

    @Column(name = "fecha_registro", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp fechaRegistro = new Timestamp(System.currentTimeMillis());

    @Column(name = "password")
    private String password; // Almacena la contraseña encriptada



}