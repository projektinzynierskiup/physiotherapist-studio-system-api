package up.krakow.pchysioterapist.api.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AppointmentResponseDTO {

    private LocalDateTime localDate;
    private List<SimpleAppointmentDTO> simpleAppointmentDTO;
}
