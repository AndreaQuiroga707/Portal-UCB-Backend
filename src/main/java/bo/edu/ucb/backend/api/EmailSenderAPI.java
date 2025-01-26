package bo.edu.ucb.backend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bo.edu.ucb.backend.bl.EmailSenderBL;
import bo.edu.ucb.backend.dto.EmailDTO;

@RestController
public class EmailSenderAPI {
    @Autowired
    private EmailSenderBL emailSenderBL;

    @PostMapping(path = "api/v1/email/")
    public EmailDTO sendEmail(@RequestBody EmailDTO emailDTO) {
        emailSenderBL.sendSimpleEmail(emailDTO.getToEmail(), emailDTO.getSubject(), emailDTO.getBody());
        return emailDTO;
    }
}
