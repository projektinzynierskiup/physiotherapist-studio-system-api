package up.krakow.pchysioterapist.api.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import up.krakow.pchysioterapist.api.model.enums.EAppointmentType;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MassageDTO {
    private Integer id;
    private String massageName;
    private String description;
    private String appointmentType;
}
