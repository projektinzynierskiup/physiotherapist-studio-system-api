package up.krakow.pchysioterapist.api.controller;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.krakow.pchysioterapist.api.dto.InfoDTO;
import up.krakow.pchysioterapist.api.dto.NewsletterDTO;
import up.krakow.pchysioterapist.api.service.NewsletterService;
import up.krakow.pchysioterapist.api.utils.ControllerEndpoints;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(value = ControllerEndpoints.GUEST + "/newsletter")
@AllArgsConstructor
public class NewsletterController {

    private final NewsletterService newsletterService;

    @PostMapping
    public ResponseEntity<InfoDTO> signToNewsletter(@RequestBody NewsletterDTO dto) throws MessagingException, IOException {
        newsletterService.signToNewsletter(dto.getUserEmail());
        return ResponseEntity.ok(new InfoDTO("Dodano do newslettera!"));
    }

    @DeleteMapping("/{deleteKey}")
    public ResponseEntity<InfoDTO> signOutFromNewsletterByEmail(@PathVariable UUID deleteKey) {
        newsletterService.signOutFromNewsletterByEmail(deleteKey);
        return ResponseEntity.ok(new InfoDTO("Wypisano z newslettera!"));
    }

    @GetMapping("/{newsletterEmail}")
    public ResponseEntity<InfoDTO> isInNewsletter(@PathVariable String newsletterEmail) {
        boolean z = newsletterService.isSignedIn(newsletterEmail);

        return ResponseEntity.ok(new InfoDTO(z == true ? "true" : "false"));
    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity<InfoDTO> remove(@PathVariable String email) {
        newsletterService.remove(email);
        return ResponseEntity.ok(new InfoDTO("Usunieto z newslettera"));
    }
}
