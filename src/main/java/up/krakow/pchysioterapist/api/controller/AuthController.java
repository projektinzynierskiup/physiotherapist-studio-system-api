package up.krakow.pchysioterapist.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import up.krakow.pchysioterapist.api.config.jwt.JwtUtils;
import up.krakow.pchysioterapist.api.controller.utils.ControllerEndpoints;
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
    public ResponseEntity<String> login(@RequestBody UserCredentialsDTO userCredentialsDTO) {
        System.out.println("weszlo");
        userService.isUserValid(userCredentialsDTO);
        System.out.println("weszlo1");
        Authentication authentication = jwtUtils.getAuthentication(userCredentialsDTO);
        authentication.getName();
        System.out.println("weszlo2");
        return ResponseEntity.ok(jwtUtils.generateToken(authentication));
    }

    @PostMapping(value = ControllerEndpoints.REGISTER)
    public ResponseEntity<String> register(@RequestBody UsersDTO userDTO) {
        userService.save(userDTO);
        return ResponseEntity.ok("ok jest");
    }
}
