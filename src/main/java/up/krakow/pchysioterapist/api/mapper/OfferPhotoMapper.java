package up.krakow.pchysioterapist.api.mapper;

import org.mapstruct.Mapper;
import up.krakow.pchysioterapist.api.dto.OfferPhotoDTO;
import up.krakow.pchysioterapist.api.model.OfferPhoto;

@Mapper
public interface OfferPhotoMapper {

    OfferPhoto mapToOfferPhoto(OfferPhotoDTO dto);

    OfferPhotoDTO mapToOfferPhotoDTO(OfferPhoto offerPhoto);
}
