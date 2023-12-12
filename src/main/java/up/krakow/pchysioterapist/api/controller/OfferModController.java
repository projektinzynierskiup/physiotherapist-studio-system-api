package up.krakow.pchysioterapist.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.krakow.pchysioterapist.api.dto.InfoDTO;
import up.krakow.pchysioterapist.api.dto.OfferDTO;
import up.krakow.pchysioterapist.api.mapper.OfferMapper;
import up.krakow.pchysioterapist.api.service.OfferService;
import up.krakow.pchysioterapist.api.utils.ControllerEndpoints;

@RestController
@RequestMapping(value = ControllerEndpoints.MOD + "/offer")
@AllArgsConstructor
public class OfferModController {

    private final OfferService offerService;

    private OfferMapper offerMapper;

    @PostMapping
    ResponseEntity<OfferDTO> createOffer(@RequestBody OfferDTO dto) {
        return ResponseEntity.ok(offerMapper.mapToOfferDTO(offerService.createOffer(dto)));
    }

    @PutMapping("/{offerId}")
    ResponseEntity<OfferDTO> editOffer(@RequestBody OfferDTO dto, @PathVariable Integer offerId) {
        return ResponseEntity.ok(offerMapper.mapToOfferDTO(offerService.editOffer(dto, offerId)));
    }

    @DeleteMapping("/{offerId}")
    ResponseEntity<InfoDTO> deleteOffer(@PathVariable Integer offerId) {
        offerService.deleteOffer(offerId);
        return ResponseEntity.ok(new InfoDTO("Oferta usunięta"));
    }
}
