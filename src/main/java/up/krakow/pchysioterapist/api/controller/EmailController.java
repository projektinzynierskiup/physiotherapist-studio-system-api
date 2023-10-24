package up.krakow.pchysioterapist.api.controller;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.krakow.pchysioterapist.api.model.Email;
import up.krakow.pchysioterapist.api.service.EmailService;
import up.krakow.pchysioterapist.api.service.EmailServiceImpl;
import up.krakow.pchysioterapist.api.utils.ControllerEndpoints;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping(ControllerEndpoints.GUEST + ControllerEndpoints.EMAIL)
@AllArgsConstructor
public class EmailController {

    private EmailService emailService;

    @PostMapping(value = "/confirmation")
    public ResponseEntity<String> confirmation(@RequestBody Email email) throws MessagingException, IOException {
        emailService.execute(email);
        return ResponseEntity.ok("git");
    }
}
