package up.krakow.pchysioterapist.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import up.krakow.pchysioterapist.api.validator.ValidatorUtils;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersDTO {
    @NotNull
    @Email
    @Min(5)
    private String email;
    @Size(min = 3, max = 20)
    private String username;
    @Size(min = 8, max = 31)
    @Pattern(regexp = ValidatorUtils.NO_WHITE_SPACE, message = "Nie może zawierać białych znaków")
    private String password;
}
