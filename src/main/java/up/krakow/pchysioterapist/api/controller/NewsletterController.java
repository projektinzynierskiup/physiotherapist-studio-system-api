package up.krakow.pchysioterapist.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import up.krakow.pchysioterapist.api.dto.NewsletterDTO;
import up.krakow.pchysioterapist.api.mapper.NewsletterMapper;
import up.krakow.pchysioterapist.api.service.NewsletterService;
import up.krakow.pchysioterapist.api.utils.ControllerEndpoints;

@RestController
@RequestMapping(value = ControllerEndpoints.GUEST + "/newsletter")
@AllArgsConstructor
public class NewsletterController {

    private final NewsletterService newsletterService;
    private final NewsletterMapper newsletterMapper;

    @PostMapping
    public ResponseEntity<NewsletterDTO> signToNewsletter(@RequestBody NewsletterDTO dto) {
        return ResponseEntity.ok(newsletterMapper.mapToNewsletterDTO(newsletterService.signToNewsletter(dto.getUserEmail())));
    }
}
