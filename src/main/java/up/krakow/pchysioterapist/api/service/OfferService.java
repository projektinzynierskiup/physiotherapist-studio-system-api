package up.krakow.pchysioterapist.api.service;

import up.krakow.pchysioterapist.api.dto.OfferDTO;
import up.krakow.pchysioterapist.api.model.Offer;
import up.krakow.pchysioterapist.api.model.enums.EAppointmentType;

import java.util.List;
import java.util.UUID;

public interface OfferService {

    Offer createOffer(OfferDTO dto);

    Offer getOfferById(UUID offerId);

    Offer editOffer(OfferDTO dto, UUID offerId);

    void deleteOffer(UUID offerId);

    List<Offer> getAllOffers();

    List<Offer> getAllOfferByType(EAppointmentType type);
}
