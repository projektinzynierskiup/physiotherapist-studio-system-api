package up.krakow.pchysioterapist.api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AppointmentDTO {
    private Integer id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer userId;
    private UsersDTO usersDTO;
    private Integer massageId;
    private MassageDTO massageDTO;
    private String status;
}
