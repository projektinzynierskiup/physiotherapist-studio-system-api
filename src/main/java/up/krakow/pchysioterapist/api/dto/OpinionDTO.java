package up.krakow.pchysioterapist.api.dto;

import jakarta.persistence.Column;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OpinionDTO {
    private Integer id;
    @NotNull
    private String username;
    @NotNull
    private String description;
    @NotNull
    private Integer rate;
    private LocalDate localDate;
}
