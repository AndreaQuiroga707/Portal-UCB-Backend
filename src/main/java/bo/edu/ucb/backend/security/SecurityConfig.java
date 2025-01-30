
package bo.edu.ucb.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;

    public SecurityConfig(JwtTokenFilter jwtTokenFilter) {
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowCredentials(true);
                    config.addAllowedOriginPattern("*"); // Cambiar para producción según sea necesario
                    config.addAllowedHeader("*");
                    config.addAllowedMethod("*");
                    return config;
                }))
                .headers(headers -> headers
                        .contentSecurityPolicy(csp -> csp
                                .policyDirectives("default-src 'self'; script-src 'self'; style-src 'self'; img-src 'self'; connect-src 'self'")
                        )
                        .frameOptions(frameOptions -> frameOptions.deny()) // Protección contra Clickjacking
                        .contentTypeOptions(withDefaults()) // Evita el sniffing de tipos de contenido
                )

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/usuario/auth",
                                "/api/v1/usuario/new/token",
                                "/api/v1/usuario/new/token/password").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/v1/noticias/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/eventos/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/sociedades/cientificas/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/centros/investigaciones/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/grupos/investigaciones/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/institutos/investigaciones/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/v1/noticias/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/noticias/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/noticias/**").hasAuthority("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/v1/eventos/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/eventos/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/eventos/**").hasAuthority("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/v1/sociedades/cientificas/**").hasAuthority("ADMIN2")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/sociedades/cientificas/**").hasAuthority("ADMIN2")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/sociedades/cientificas/**").hasAuthority("ADMIN2")

                        .requestMatchers(HttpMethod.POST, "/api/v1/centros/investigaciones/**").hasAuthority("ADMIN2")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/centros/investigaciones/**").hasAuthority("ADMIN2")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/centros/investigaciones/**").hasAuthority("ADMIN2")

                        .requestMatchers(HttpMethod.POST, "/api/v1/grupos/investigaciones/**").hasAuthority("ADMIN2")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/grupos/investigaciones/**").hasAuthority("ADMIN2")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/grupos/investigaciones/**").hasAuthority("ADMIN2")

                        .requestMatchers(HttpMethod.POST, "/api/v1/institutos/investigaciones/**").hasAuthority("ADMIN2")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/institutos/investigaciones/**").hasAuthority("ADMIN2")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/institutos/investigaciones/**").hasAuthority("ADMIN2")

                        .requestMatchers("/api/v1/usuario/**").hasAuthority("GESTOR DE USUARIOS")
                        .requestMatchers("/api/v1/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}



//package bo.edu.ucb.backend.security;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//
//@Configuration
//public class SecurityConfig {
//
//    private final JwtTokenFilter jwtTokenFilter;
//
//    public SecurityConfig(JwtTokenFilter jwtTokenFilter) {
//        this.jwtTokenFilter = jwtTokenFilter;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable())
//                .cors(cors -> cors.configurationSource(request -> {
//                    CorsConfiguration config = new CorsConfiguration();
//                    config.setAllowCredentials(true);
//                    config.addAllowedOriginPattern("*"); // Cambiar para producción según sea necesario
//                    config.addAllowedHeader("*");
//                    config.addAllowedMethod("*");
//                    return config;
//                }))
//
//                .authorizeHttpRequests(auth -> auth
//                        // Rutas públicas para /api/v1/usuario
//                        .requestMatchers("/api/v1/usuario/auth",
//                                "/api/v1/usuario/new/token",
//                                "/api/v1/usuario/new/token/password").permitAll()
//
//                        // Permitir GET para /api/v1/noticias/
//                        .requestMatchers(HttpMethod.GET, "/api/v1/noticias/**").permitAll()
//
//                        // Permitir GET para /api/v1/eventos/
//                        .requestMatchers(HttpMethod.GET, "/api/v1/eventos/**").permitAll()
//
//                        // Permitir GET para /api/v1/sociedades/cientificas/
//                        .requestMatchers(HttpMethod.GET, "/api/v1/sociedades/cientificas/**").permitAll()
//
//                        // Permitir GET para /api/v1/centros/investigaciones/
//                        .requestMatchers(HttpMethod.GET, "/api/v1/centros/investigaciones/**").permitAll()
//
//                        // Permitir GET para /api/v1/grupos/investigaciones/
//                        .requestMatchers(HttpMethod.GET, "/api/v1/grupos/investigaciones/**").permitAll()
//
//                        // Permitir GET para /api/v1/institutos/investigaciones/
//                        .requestMatchers(HttpMethod.GET, "/api/v1/institutos/investigaciones/**").permitAll()
//
//
//
//                        // Proteger otros métodos (POST, PUT, DELETE) para /api/v1/noticias/
//                        .requestMatchers(HttpMethod.POST, "/api/v1/noticias/**").hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/api/v1/noticias/**").hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/v1/noticias/**").hasAuthority("ADMIN")
//
//                        .requestMatchers(HttpMethod.POST, "/api/v1/eventos/**").hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/api/v1/eventos/**").hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/v1/eventos/**").hasAuthority("ADMIN")
//
//                        .requestMatchers(HttpMethod.POST, "/api/v1/sociedades/cientificas/**").hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/api/v1/sociedades/cientificas/**").hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/v1/sociedades/cientificas/**").hasAuthority("ADMIN")
//
//                        .requestMatchers(HttpMethod.POST, "/api/v1/centros/investigaciones/**").hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/api/v1/centros/investigaciones/**").hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/v1/centros/investigaciones/**").hasAuthority("ADMIN")
//
//                        .requestMatchers(HttpMethod.POST, "/api/v1/grupos/investigaciones/**").hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/api/v1/grupos/investigaciones/**").hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/v1/grupos/investigaciones/**").hasAuthority("ADMIN")
//
//                        .requestMatchers(HttpMethod.POST, "/api/v1/institutos/investigaciones/**").hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/api/v1/institutos/investigaciones/**").hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/v1/institutos/investigaciones/**").hasAuthority("ADMIN")
//
//
//
//                        // Rutas protegidas para /api/v1/usuario excepto las públicas
//                        .requestMatchers("/api/v1/usuario/**").hasAuthority("GESTOR DE USUARIOS")
//
//                        // Todas las demás rutas dentro de /api/v1/** son públicas
//                        .requestMatchers("/api/v1/**").permitAll()
//
//                        // Todas las demás rutas necesitan autenticación básica
//                        .anyRequest().authenticated()
//                )
//                // Añadir el filtro JWT antes del filtro de autenticación por usuario/contraseña
//                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//        return authConfig.getAuthenticationManager();
//    }
//}
