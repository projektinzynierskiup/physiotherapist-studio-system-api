package up.krakow.pchysioterapist.api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import up.krakow.pchysioterapist.api.dto.OfferDTO;
import up.krakow.pchysioterapist.api.model.Offer;
import up.krakow.pchysioterapist.api.model.enums.EAppointmentType;
import up.krakow.pchysioterapist.api.repository.OfferRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OfferServiceImpl implements OfferService{

    private final OfferRepository offerRepository;

    @Override
    public Offer createOffer(OfferDTO dto) {
        Offer offer = new Offer();
        offer.setDescription(dto.getDescription());
        offer.setDuration(dto.getDuration());
        offer.setName(dto.getName());
        offer.setPrice(dto.getPrice());
        offer.setType(dto.getType());
        offerRepository.save(offer);
        return offer;
    }

    @Override
    public Offer getOfferById(UUID offerId) {
        return offerRepository.findById(offerId).orElseThrow(() ->
                new NoSuchElementException("Offer with id:" + offerId + " does not exist!"));
    }

    @Override
    public Offer editOffer(OfferDTO dto, UUID offerId) {
        Offer offer = offerRepository.findById(offerId).orElseThrow(() ->
                new NoSuchElementException("Offer with id:" + offerId + " does not exist!"));
        offer.setDescription(dto.getDescription());
        offer.setDuration(dto.getDuration());
        offer.setName(dto.getName());
        offer.setPrice(dto.getPrice());
        offer.setType(dto.getType());
        offerRepository.save(offer);
        return offer;
    }

    @Override
    public void deleteOffer(UUID offerId) {
        offerRepository.deleteById(offerId);
    }

    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    @Override
    public List<Offer> getAllOfferByType(EAppointmentType type) {
        return offerRepository.findAllByType(type);
    }
}
