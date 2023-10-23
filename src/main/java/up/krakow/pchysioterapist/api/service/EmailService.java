package up.krakow.pchysioterapist.api.service;

import jakarta.mail.MessagingException;
import up.krakow.pchysioterapist.api.model.Email;

import java.io.IOException;
import java.time.LocalDateTime;

public interface EmailService {
    void sendInvitation(String recipientEmail,
                   LocalDateTime startTime,
                   LocalDateTime endTime,
                   String eventName,
                   String defaultDescription,
                   String description,
                        String decision) throws MessagingException, IOException;

    String generateICSContent(LocalDateTime startTime,
                              LocalDateTime endTime,
                              String eventName,
                              String description);
    void execute(Email email) throws MessagingException, IOException;
}
