package up.krakow.pchysioterapist.api.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalendarDTO {
    private LocalDate localDate;
    private List<UsersDTO> usersDTOList;
}
