package up.krakow.pchysioterapist.api.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import up.krakow.pchysioterapist.api.model.Users;
import up.krakow.pchysioterapist.api.model.enums.EAppointmentType;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class AppointmentDTO {

    private UUID id;
    private LocalDate startDate;
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private EAppointmentType type;
    private Users userId;
}
