package up.krakow.pchysioterapist.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import up.krakow.pchysioterapist.api.model.Massage;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfferDTO {

    private Integer id;
    private String name;
    private double duration;
    private int price;
    private Massage massage;
}
