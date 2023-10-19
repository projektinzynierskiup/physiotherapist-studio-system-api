package up.krakow.pchysioterapist.api.service;

import up.krakow.pchysioterapist.api.dto.OfferPhotoDTO;
import up.krakow.pchysioterapist.api.model.OfferPhoto;

import java.util.UUID;

public interface OfferPhotoService {

    OfferPhoto addOfferPhoto(OfferPhotoDTO dto);

    OfferPhoto getOfferPhoto(UUID offerPhotoId);

    OfferPhoto updateOfferPhoto(OfferPhotoDTO dto, UUID offerPhotoId);

    void deleteOfferPhoto(UUID offerPhotoId);
}
