package up.krakow.pchysioterapist.api.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalendarDTO {
    private LocalDateTime localDate;
    private List<UsersDTO> usersDTOList;
}
