package up.krakow.pchysioterapist.api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SimpleAppointmentDTO {

    private Integer id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
