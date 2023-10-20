package up.krakow.pchysioterapist.api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersDTO {
    private String email;
    private String username;
    private String password;
}
