package up.krakow.pchysioterapist.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import up.krakow.pchysioterapist.api.model.Massage;
import up.krakow.pchysioterapist.api.model.Users;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private Integer id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Users user;
    private Massage massage;
    private String status;
}
