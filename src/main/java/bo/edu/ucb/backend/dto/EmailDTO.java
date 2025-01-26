package bo.edu.ucb.backend.dto;

public class EmailDTO {
    private String toEmail;
    private String subject;
    private String body;

    public EmailDTO() {
    }

    public EmailDTO(String toEmail, String subject, String body) {
        this.toEmail = toEmail;
        this.subject = subject;
        this.body = body;
    }

    public String getToEmail() {
        return toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}
