package up.krakow.pchysioterapist.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import up.krakow.pchysioterapist.api.model.Offer;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class OfferPhotoDTO {

    private UUID id;
    private String photoName;
    private byte[] photoByte;
    private String photoType;
    private Offer offerId;
}
