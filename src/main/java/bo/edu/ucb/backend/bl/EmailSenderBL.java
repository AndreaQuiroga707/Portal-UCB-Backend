package bo.edu.ucb.backend.bl;

import bo.edu.ucb.backend.dto.SuscripcionesDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void sendSimpleEmail(String toEmail,
                                String subject,
                                String body
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("fromemail@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Send...");
    }

    public void sendEmailSuscripcion(String subject, String body) {
        try {
            List<SuscripcionesDTO> correos = suscripcionesBL.findAllSuscripcionesList();
            if (correos.isEmpty()) {
                LOGGER.info("No hay correos para enviar");
                return;
            }
            try {
                for (SuscripcionesDTO correo : correos) {
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setFrom("fromemail@gmail.com");
                    message.setTo(correo.getCorreo());
                    message.setText(body);
                    message.setSubject(subject);
                    mailSender.send(message);
                    LOGGER.info("Mail Send... to {}", correo.getCorreo());
                    System.out.println("Mail Send...");
                }
            } catch (Exception e) {
                throw new RuntimeException("Error al enviar el correo " + e.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al listar las suscripciones");
        }
    }
}