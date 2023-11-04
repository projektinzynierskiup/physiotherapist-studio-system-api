package up.krakow.pchysioterapist.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfferPhotoDTO {

    private Integer id;
    private String photoName;
    private byte[] photoByte;
    private String photoType;
    private Integer offerId;
}
