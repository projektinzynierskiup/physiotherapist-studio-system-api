package up.krakow.pchysioterapist.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.krakow.pchysioterapist.api.dto.OfferDTO;
import up.krakow.pchysioterapist.api.mapper.OfferMapper;
import up.krakow.pchysioterapist.api.model.enums.EAppointmentType;
import up.krakow.pchysioterapist.api.service.OfferService;
import up.krakow.pchysioterapist.api.utils.ControllerEndpoints;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = ControllerEndpoints.GUEST + "/offer")
@AllArgsConstructor
public class OfferController {

    private final OfferService offerService;

    private OfferMapper offerMapper;

    @PostMapping
    ResponseEntity<OfferDTO> createOffer(@RequestBody OfferDTO dto) {
        return ResponseEntity.ok(offerMapper.mapToOfferDTO(offerService.createOffer(dto)));
    }

    @GetMapping("/{offerId}")
    ResponseEntity<OfferDTO> getOffer(@PathVariable Integer offerId) {
        return ResponseEntity.ok(offerMapper.mapToOfferDTO(offerService.getOfferById(offerId)));
    }

    @PutMapping("/{offerId}")
    ResponseEntity<OfferDTO> editOffer(@RequestBody OfferDTO dto, @PathVariable Integer offerId) {
        return ResponseEntity.ok(offerMapper.mapToOfferDTO(offerService.editOffer(dto, offerId)));
    }

    @DeleteMapping("/{offerId}")
    void deleteOffer(@PathVariable Integer offerId) {
        //TODO: Create MessageResponse
        offerService.deleteOffer(offerId);
    }

    @GetMapping("/all")
    ResponseEntity<List<OfferDTO>> getAllOffers() {
        return ResponseEntity.ok(offerMapper.mapToListOfOffersDTO(offerService.getAllOffers()));
    }
}
