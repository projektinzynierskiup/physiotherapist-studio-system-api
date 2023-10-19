package up.krakow.pchysioterapist.api.mapper;

import org.mapstruct.Mapper;
import up.krakow.pchysioterapist.api.dto.OfferDTO;
import up.krakow.pchysioterapist.api.model.Offer;

import java.util.List;

@Mapper
public interface OfferMapper {

    Offer mapToOffer(OfferDTO dto);

    OfferDTO mapToOfferDTO(Offer offer);

    List<OfferDTO> mapToListOfOffersDTO(List<Offer> offers);
}
