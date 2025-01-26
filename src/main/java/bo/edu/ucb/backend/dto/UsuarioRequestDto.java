package bo.edu.ucb.backend.dto;


import lombok.Data;

@Data
public class UsuarioRequestDto {
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String password;

}

