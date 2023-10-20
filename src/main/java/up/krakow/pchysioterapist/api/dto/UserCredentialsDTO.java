package up.krakow.pchysioterapist.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import up.krakow.pchysioterapist.api.utils.ValidatorUtils;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCredentialsDTO {
    @NotNull
    @Size(min = 5, max = 40)
    @Email
    private String email;
    @NotNull
    @Size(min = 8, max = 31)
    @Pattern(regexp = ValidatorUtils.NO_WHITE_SPACE, message = "Nie może zawierać białych znaków")
    private String password;
}