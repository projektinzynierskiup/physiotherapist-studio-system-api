package up.krakow.pchysioterapist.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import up.krakow.pchysioterapist.api.config.jwt.JwtUtils;
import up.krakow.pchysioterapist.api.controller.advice.ResponseHelper;
import up.krakow.pchysioterapist.api.dto.TokenDTO;
import up.krakow.pchysioterapist.api.model.Users;
import up.krakow.pchysioterapist.api.utils.ControllerEndpoints;
import up.krakow.pchysioterapist.api.dto.UserCredentialsDTO;
import up.krakow.pchysioterapist.api.dto.UsersDTO;
import up.krakow.pchysioterapist.api.service.UserService;

@RestController
@RequestMapping(value = ControllerEndpoints.GUEST)
@AllArgsConstructor
public class AuthController {
    private final JwtUtils jwtUtils;
    private final UserService userService;
    @PostMapping(value = ControllerEndpoints.LOGIN)
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid UserCredentialsDTO userCredentialsDTO) {
        userService.isUserValid(userCredentialsDTO);
        Users users = userService.getUserByEmail(userCredentialsDTO.getEmail());
        return ResponseEntity.ok(new TokenDTO(jwtUtils.generateToken(users)));
    }

    @PostMapping(value = ControllerEndpoints.REGISTER)
    public ResponseEntity<?> register(@RequestBody @Valid UsersDTO userDTO) throws Exception {
        userService.registerUser(userDTO);
        return ResponseHelper.response200("Zarejestrowano");
    }
}
