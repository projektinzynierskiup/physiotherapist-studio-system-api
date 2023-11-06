package up.krakow.pchysioterapist.api.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import up.krakow.pchysioterapist.api.utils.ValidatorUtils;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestartPasswordDTO {
    private String uuid;
    @Size(min = 8, max = 31)
    @Pattern(regexp = ValidatorUtils.NO_WHITE_SPACE, message = "Nie może zawierać białych znaków")
    private String password;
}
