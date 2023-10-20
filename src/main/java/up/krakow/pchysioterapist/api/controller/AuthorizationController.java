package up.krakow.pchysioterapist.api.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import up.krakow.pchysioterapist.api.config.jwt.JwtUtils;
import up.krakow.pchysioterapist.api.dto.UserCredentialsDTO;

@RestController
public class AuthorizationController {
    private JwtUtils jwtUtils;
    private AuthenticationManager authenticationManager;
    public AuthorizationController(JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/x")
    String getToken(@RequestBody UserCredentialsDTO userCredentialsDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userCredentialsDTO.getEmail(),
                        userCredentialsDTO.getPassword()));
        return jwtUtils.generateToken(authentication);
    }

    @GetMapping("/pw")
    String getPw() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String pw = bCryptPasswordEncoder.encode("password");
        return pw;
    }

    @GetMapping("/test")
    String test() {
        return "smiga";
    }
}
