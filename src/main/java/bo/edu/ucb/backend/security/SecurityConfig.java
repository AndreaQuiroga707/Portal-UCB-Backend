package bo.edu.ucb.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;

    public SecurityConfig(JwtTokenFilter jwtTokenFilter) {
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Rutas públicas dentro de /api/v1/usuario
                        .requestMatchers("/api/v1/usuario/auth",
                                "/api/v1/usuario/new/token",
                                "/api/v1/usuario/new/token/password").permitAll()
                        // Rutas protegidas dentro de /api/v1/usuario que requieren el rol GESTOR DE USUARIOS
                        .requestMatchers("/api/v1/usuario/**").hasAuthority("GESTOR DE USUARIOS")
                        // Todas las demás rutas dentro de /api/v1/** son públicas
                        .requestMatchers("/api/v1/**").permitAll()
                        // Todas las demás rutas necesitan autenticación básica
                        .anyRequest().authenticated()
                )
                // Añadir el filtro JWT antes del filtro de autenticación por usuario/contraseña
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
