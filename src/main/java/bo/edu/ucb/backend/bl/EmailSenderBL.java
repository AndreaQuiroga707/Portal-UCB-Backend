package bo.edu.ucb.backend.bl;

import bo.edu.ucb.backend.entity.Suscripciones;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailSenderBL {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSenderBL.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SuscripcionesBL suscripcionesBL;

    // Usamos @Value para obtener el correo configurado
    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendSimpleEmail(String toEmail, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail); // Usar el correo configurado en application.properties
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);
        } catch (Exception e) {
            LOGGER.error("Error al enviar correo: {}", e.getMessage());
            throw new RuntimeException("Error al enviar correo: " + e.getMessage());
        }
    }

    public void sendEmailSuscripcion(String subject, String body) {
        try {
            List<Suscripciones> correos = suscripcionesBL.findAllSuscripcionesList();
            if (correos.isEmpty()) {
                return;
            }

            for (Suscripciones correo : correos) {
                try {
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setFrom(fromEmail); // Usar el correo configurado en application.properties
                    message.setTo(correo.getCorreo());
                    message.setText(body);
                    message.setSubject(subject);
                    mailSender.send(message);
                } catch (Exception e) {
                    LOGGER.error("Error al enviar correo a {}: {}", correo.getCorreo(), e.getMessage());
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error al listar suscripciones: {}", e.getMessage());
            throw new RuntimeException("Error al listar suscripciones.");
        }
    }

    public void sendPasswordResetEmail(String to, String token) {
        String subject = "Restablecimiento de Contraseña";
        String link = "token: " + token;
        String body = "Su cuenta está bloqueada. Por favor, use el siguiente enlace para restablecer su contraseña:\n\n" + link;

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail); // Usar el correo configurado en application.properties
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);
        } catch (Exception e) {
            LOGGER.error("Error al enviar correo de restablecimiento a {}: {}", to, e.getMessage());
            throw new RuntimeException("Error al enviar correo de restablecimiento: " + e.getMessage());
        }
    }
}
