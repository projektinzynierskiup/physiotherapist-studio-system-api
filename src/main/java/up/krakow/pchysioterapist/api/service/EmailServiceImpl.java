package up.krakow.pchysioterapist.api.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import up.krakow.pchysioterapist.api.model.Email;
import up.krakow.pchysioterapist.api.model.RestartPassword;
import up.krakow.pchysioterapist.api.model.configuration.EmailConfiguration;
import up.krakow.pchysioterapist.api.model.enums.EEmailStatus;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;
import java.util.UUID;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private EmailConfiguration emailConfiguration;

    public void sendInvitation(String recipientEmail,
                               LocalDateTime startTime,
                               LocalDateTime endTime,
                               String eventName,
                               String defaultDescription,
                               String description,
                               String decision,
                               EEmailStatus emailStatus) throws MessagingException, IOException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(recipientEmail);
        helper.setSubject(decision);
        helper.setText(defaultDescription + description);

        if (EnumSet.of(EEmailStatus.ACCEPTATION,
                EEmailStatus.CHANGE).contains(emailStatus)) {
            String icsContent = generateICSContent(startTime, endTime, eventName, description);
            ByteArrayDataSource dataSource = new ByteArrayDataSource(icsContent, "text/calendar");
            helper.addAttachment("invitation.ics", dataSource);
        }

        emailSender.send(mimeMessage);
    }

    @Override
    public void sendNewsletterConfirmation(String recipientEmail, UUID deleteKey) throws MessagingException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(recipientEmail);
        helper.setSubject("Newsletter");
        String deleteNewsletterUrl = "localhost:4200/guest/newsletter/" + deleteKey;
        helper.setText("<html><body><p>Zapisano do newslettera!</p><br>Kliknij w link, aby wypisać się z newslettera <a href=\""+ deleteNewsletterUrl + "\">Usuń z newslettera</a></body></html>", true);

        emailSender.send(mimeMessage);
    }

    @Async
    public void restartPassworEmail(String email, RestartPassword restartPassword) throws MessagingException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(email);
        helper.setSubject("Zresetuj swoje haslo");
        String restartPasswordUrl = "localhost:4200/guest/users/restartpassword/" + restartPassword.getUuid();
        System.out.println(restartPasswordUrl);
        helper.setText("<html><head></head><body style=\"color: black;\"><p>Rozpoczął się proces restartowania hasła dla systemu masaży</p><br>Kliknij w link, aby <a style=\"color: #15c; text-decoration:underline; cursos: pointer;\">zrestartować swoje hasło.</a></body></html>", true);
        emailSender.send(mimeMessage);
    }

    public String generateICSContent(LocalDateTime startTime,
                                     LocalDateTime endTime,
                                     String eventName,
                                     String description) {
        return "BEGIN:VCALENDAR\r\n" +
                "VERSION:2.0\r\n" +
                "PRODID:-//YourCompany//YourApp//EN\r\n" +
                "BEGIN:VEVENT\r\n" +
                "UID:" + UUID.randomUUID().toString() + "\r\n" +
                "DTSTAMP:" + DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'").format(LocalDateTime.now()) + "\r\n" +
                "DTSTART:" + DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'").format(startTime) + "\r\n" +
                "DTEND:" + DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'").format(endTime) + "\r\n" +
                "ORGANIZER:MAILTO:"+"Twoj masazysta 69"+"\n" +
                "SUMMARY:" + eventName + "\r\n" +
                "DESCRIPTION:" + description + "\r\n" +
                "END:VEVENT\r\n" +
                "END:VCALENDAR";
    }

    @Override
    public void execute(Email email) throws MessagingException, IOException {
        switch (email.getEmailStatus()) {
            case ACCEPTATION ->
                //id wizyty - findby visit
                    sendInvitation(email.getRecipientEmail(),
                            email.getStartTime(),
                            email.getEndTime(),
                            email.getEventName(),
                            emailConfiguration.getDefaultAcceptDescription(),
                            email.getDescription(),
                            emailConfiguration.getAcceptation(),
                            email.getEmailStatus());


            //zmiana statusu w bazie
            case CHANGE -> sendInvitation(email.getRecipientEmail(),
                    email.getStartTime(),
                    email.getEndTime(),
                    email.getEventName(),
                    emailConfiguration.getDefaultAcceptDescription(),
                    email.getDescription(),
                    emailConfiguration.getChange(),
                    email.getEmailStatus());


            //zmiana statusu w bazie
            case REJECTION ->
                //id wizyty - findby visit
                    sendInvitation(email.getRecipientEmail(),
                            email.getStartTime(),
                            email.getEndTime(),
                            email.getEventName(),
                            emailConfiguration.getDefaultRejectDescription(),
                            email.getDescription(),
                            emailConfiguration.getRejection(),
                            email.getEmailStatus());


            //usuniecie rekordu z bazy
            case BAN ->
                //id wizyty - findby visit
                    sendInvitation(email.getRecipientEmail(),
                            email.getStartTime(),
                            email.getEndTime(),
                            email.getEventName(),
                            emailConfiguration.getDefaultRejectDescription(),
                            email.getDescription(),
                            emailConfiguration.getRejection(),
                            email.getEmailStatus());


            //zablokowanie możliwości umawiania sie na wizyty przez GUESTA+
            //usunięcie rekordu z bazy
            default -> {
                log.info(email.toString());
                throw new MessagingException("Bad credentails");
            }
        }
    }
}
