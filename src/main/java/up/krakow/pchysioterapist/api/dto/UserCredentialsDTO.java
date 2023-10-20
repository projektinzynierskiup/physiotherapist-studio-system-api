package up.krakow.pchysioterapist.api.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.Validator;
import jakarta.validation.constraints.*;
import lombok.*;
import up.krakow.pchysioterapist.api.validator.ValidatorUtils;


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
