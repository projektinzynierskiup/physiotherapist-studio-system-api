package up.krakow.pchysioterapist.api.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import up.krakow.pchysioterapist.api.model.enums.EAppointmentType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MassageDTO {
    private Integer id;
    private String massageName;
    private String description;
    @Enumerated(EnumType.STRING)
    private EAppointmentType appointmentType;
}
