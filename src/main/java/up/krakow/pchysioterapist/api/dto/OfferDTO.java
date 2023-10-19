package up.krakow.pchysioterapist.api.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import up.krakow.pchysioterapist.api.model.enums.EAppointmentType;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class OfferDTO {

    private UUID id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private EAppointmentType type;
    private double duration;
    private int price;
}
