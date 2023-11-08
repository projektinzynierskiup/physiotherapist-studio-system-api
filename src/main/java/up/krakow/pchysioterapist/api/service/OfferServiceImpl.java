package up.krakow.pchysioterapist.api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import up.krakow.pchysioterapist.api.dto.OfferDTO;
import up.krakow.pchysioterapist.api.model.Offer;
import up.krakow.pchysioterapist.api.model.enums.EAppointmentType;
import up.krakow.pchysioterapist.api.repository.OfferRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class OfferServiceImpl implements OfferService{

    private final OfferRepository offerRepository;

    @Override
    public Offer createOffer(OfferDTO dto) {
        Offer offer = new Offer();
        offer.setDuration(dto.getDuration());
        offer.setName(dto.getName());
        offer.setPrice(dto.getPrice());
        offerRepository.save(offer);
        return offer;
    }

    @Override
    public Offer getOfferById(Integer offerId) {
        return offerRepository.findByOfferId(offerId).orElseThrow(() ->
                new NoSuchElementException("Offer with id:" + offerId + " does not exist!"));
    }

    @Override
    public Offer editOffer(OfferDTO dto, Integer offerId) {
        Offer offer = offerRepository.findById(offerId).orElseThrow(() ->
                new NoSuchElementException("Offer with id:" + offerId + " does not exist!"));
        offer.setDuration(dto.getDuration());
        offer.setName(dto.getName());
        offer.setPrice(dto.getPrice());
        offerRepository.save(offer);
        return offer;
    }

    @Override
    public void deleteOffer(Integer offerId) {
        offerRepository.deleteById(offerId);
    }

    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }
}
