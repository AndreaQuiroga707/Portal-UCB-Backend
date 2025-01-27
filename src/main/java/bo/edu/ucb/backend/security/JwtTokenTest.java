package bo.edu.ucb.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;

public class JwtTokenTest {

    private static final String SECRET = "0fSXACp7UMBmUv6ZAqYvcL6lmUM/BvAId+eL7EJWE8fHnTwuiUJ54VLXCPyIvB8RGaaQMTT9h4cFevE6KDiR5A==";

    private static Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static void main(String[] args) {
        // Token de prueba: reemplaza esto con un token generado por tu sistema
        String testToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcmRheWFhbGZhcm9AZ21haWwuY29tIiwicm9sZSI6IkdFU1RPUiBERSBVU1VBUklPUyIsImlhdCI6MTczNzk0ODUzNSwiZXhwIjoxNzM4MDM0OTM1fQ.aVTncRcmOaPU_uP44W7PRrRStKXW09m3a7STLodF8pvK89GiJ_hrybuix-KXuaRwwb5-oumnXd95s49eOXnYDw";

        try {
            Claims claims = parseToken(testToken);

            // Extraer información del token
            String email = claims.getSubject(); // Email o identificador
            String role = claims.get("role", String.class); // Rol del usuario

            System.out.println("Email: " + email);
            System.out.println("Rol: " + role);
            System.out.println("Emitido en: " + claims.getIssuedAt());
            System.out.println("Expira en: " + claims.getExpiration());

        } catch (Exception e) {
            System.out.println("Error al parsear el token: " + e.getMessage());
        }
    }
}
