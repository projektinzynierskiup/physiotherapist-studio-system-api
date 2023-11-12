package up.krakow.pchysioterapist.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import up.krakow.pchysioterapist.api.utils.ValidatorUtils;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersDTO {
    private Integer id;
    @NotNull
    @Email
    @Min(5)
    private String email;
    @Size(min = 3, max = 25)
    private String username;
    @Size(min = 2, max = 25)
    private String surname;
    @Size(min = 8, max = 31)
    @Pattern(regexp = ValidatorUtils.NO_WHITE_SPACE, message = "Nie może zawierać białych znaków")
    private String password;
    private LocalTime localTime;
    private String phone;
    private MassageDTO massageDTO;
}
