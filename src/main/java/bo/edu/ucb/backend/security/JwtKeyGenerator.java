package bo.edu.ucb.backend.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

public class JwtKeyGenerator {
    public static void main(String[] args) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String base64Key = java.util.Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("Clave secreta segura: " + base64Key);
    }
}
