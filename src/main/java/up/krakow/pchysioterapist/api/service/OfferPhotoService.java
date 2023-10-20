package up.krakow.pchysioterapist.api.service;

import up.krakow.pchysioterapist.api.dto.OfferPhotoDTO;
import up.krakow.pchysioterapist.api.model.OfferPhoto;

import java.util.UUID;

public interface OfferPhotoService {

    OfferPhoto addOfferPhoto(OfferPhotoDTO dto);

    OfferPhoto getOfferPhoto(Integer offerPhotoId);

    OfferPhoto updateOfferPhoto(OfferPhotoDTO dto, Integer offerPhotoId);

    void deleteOfferPhoto(Integer offerPhotoId);
}
