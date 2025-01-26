package bo.edu.ucb.backend.dto;

import lombok.Data;

@Data
public class UsuarioResponseDto {
    private String mensaje;
    private String token;
    private String nombre;
    private String apellido;
    private String correoElectronico;

    public UsuarioResponseDto(String mensaje, String token, String nombre, String apellido, String correoElectronico) {
        this.mensaje = mensaje;
        this.token = token;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
    }

    public UsuarioResponseDto(String mensaje) {
        this.mensaje = mensaje;
    }
}
