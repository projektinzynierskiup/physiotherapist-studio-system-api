package up.krakow.pchysioterapist.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "offer_photo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfferPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String photoName;
    private byte[] photoByte;
    private String photoType;
    private Integer offerId;
}
