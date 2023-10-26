package up.krakow.pchysioterapist.api.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import up.krakow.pchysioterapist.api.model.Massage;
import up.krakow.pchysioterapist.api.model.Users;
import up.krakow.pchysioterapist.api.model.enums.EAppointmentType;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Users users;
    private Set<Massage> massages;
}
