package bo.edu.ucb.backend.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String correoElectronico;
    private String password;
}
