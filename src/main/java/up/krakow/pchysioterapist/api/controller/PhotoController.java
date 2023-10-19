package up.krakow.pchysioterapist.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.krakow.pchysioterapist.api.dto.OfferPhotoDTO;
import up.krakow.pchysioterapist.api.mapper.OfferPhotoMapper;
import up.krakow.pchysioterapist.api.service.OfferPhotoService;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class PhotoController {

    private final OfferPhotoService offerPhotoService;

    private OfferPhotoMapper offerPhotoMapper;

    @PostMapping
    ResponseEntity<OfferPhotoDTO> addOfferPhoto(@RequestBody OfferPhotoDTO dto) {
        return ResponseEntity.ok(offerPhotoMapper.mapToOfferPhotoDTO(offerPhotoService.addOfferPhoto(dto)));
    }

    @GetMapping("/offer-photo/{offerPhotoId}")
    ResponseEntity<OfferPhotoDTO> getOfferPhoto(@PathVariable UUID offerPhotoId) {
        return ResponseEntity.ok(offerPhotoMapper.mapToOfferPhotoDTO(offerPhotoService.getOfferPhoto(offerPhotoId)));
    }

    @PutMapping("/offer-photo/{offerPhotoId}")
    ResponseEntity<OfferPhotoDTO> updateOfferPhoto(@RequestBody OfferPhotoDTO dto, @PathVariable UUID offerPhotoId) {
        return ResponseEntity.ok(offerPhotoMapper.mapToOfferPhotoDTO(offerPhotoService.updateOfferPhoto(dto, offerPhotoId)));
    }

    @DeleteMapping("/offer-photo/{offerPhotoId}")
    void deleteOfferPhoto(@PathVariable UUID offerPhotoId) {
        offerPhotoService.deleteOfferPhoto(offerPhotoId);
    }
}
