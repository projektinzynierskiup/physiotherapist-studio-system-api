package up.krakow.pchysioterapist.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentWithEmailDTO {

    private Integer id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer userId;
    private Integer massageId;
    private String status;
    private String userEmail;
    private String userPhone;
}
