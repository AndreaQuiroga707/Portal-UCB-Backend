package bo.edu.ucb.backend.dto;


import lombok.Data;

@Data
public class UsuarioResponseDto {
    private String mensaje;

    public UsuarioResponseDto(String mensaje) {
        this.mensaje = mensaje;
    }

}
