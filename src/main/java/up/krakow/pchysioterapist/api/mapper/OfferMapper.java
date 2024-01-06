package up.krakow.pchysioterapist.api.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import up.krakow.pchysioterapist.api.dto.OfferDTO;
import up.krakow.pchysioterapist.api.model.Offer;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    @Mapping(target = "massage.id",source = "massageId")
    Offer mapToOffer(OfferDTO dto);

    @InheritInverseConfiguration(name = "mapToOffer")
    OfferDTO mapToOfferDTO(Offer offer);

    List<OfferDTO> mapToListOfOffersDTO(List<Offer> offers);
}
