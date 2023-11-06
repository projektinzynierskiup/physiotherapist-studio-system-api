package up.krakow.pchysioterapist.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.krakow.pchysioterapist.api.dto.InfoDTO;
import up.krakow.pchysioterapist.api.dto.NewsletterDTO;
import up.krakow.pchysioterapist.api.service.NewsletterService;
import up.krakow.pchysioterapist.api.utils.ControllerEndpoints;

@RestController
@RequestMapping(value = ControllerEndpoints.GUEST + "/newsletter")
@AllArgsConstructor
public class NewsletterController {

    private final NewsletterService newsletterService;

    @PostMapping
    public ResponseEntity<InfoDTO> signToNewsletter(@RequestBody NewsletterDTO dto) {
        newsletterService.signToNewsletter(dto.getUserEmail());
        return ResponseEntity.ok(new InfoDTO("Dodano do newslettera!"));
    }

    @DeleteMapping
    public ResponseEntity<InfoDTO> signOutFromNewsletter(@RequestBody NewsletterDTO dto) {
        newsletterService.signOutFromNewsletter(dto.getUserEmail());
        return ResponseEntity.ok(new InfoDTO("Wypisano z newslettera!"));
    }

}
