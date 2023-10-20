package up.krakow.pchysioterapist.api.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import up.krakow.pchysioterapist.api.dto.UserCredentialsDTO;
import up.krakow.pchysioterapist.api.entity.Users;

import java.util.*;

@Component
public class JwtUtils {
    @Autowired
    private AuthenticationManager authenticationManager;
    public String generateToken(Authentication authentication){
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("role", authentication.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + JwtConstants.JWT_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, JwtConstants.JWT_SECRET)
                .compact();
    }

    public String generateToken(Users users){
        return Jwts.builder()
                .setSubject(users.getUsername())
                .claim("role", users.getRole())
                .claim("email", users.getEmail())
                .claim("id", users.getId())
                .claim("username", users.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + JwtConstants.JWT_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, JwtConstants.JWT_SECRET)
                .compact();
    }

    public List<String> getRolesFromJWT(String tokenFromCookie) {
        List<String> roles = new ArrayList<>();
        Claims claims = getJwtParserInstance()
                .parseClaimsJws(tokenFromCookie)
                .getBody();

        List<Map<String, String>> authorities = (List<Map<String, String>>) claims.get("role");

        for(Map<String, String> authority: authorities) {
            roles.add(authority.get("authority"));
        }

        return roles;
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = getJwtParserInstance()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public JwtParser getJwtParserInstance() {
        return Jwts.parser().setSigningKey(JwtConstants.JWT_SECRET);
    }

    public boolean validateToken(String token) throws Exception {
        try {
            getJwtParserInstance()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new Exception("JWT problem expired or incorrect");
        }
    }

    public String getJwtFromCookie(HttpServletRequest request) {
        if(request.getCookies() == null)
            return null;

        Optional<Cookie> cookie = Arrays.stream(request.getCookies())
                .filter(cookie1 -> CustomAuthorizationHeader.AUTHORIZATION_HEADER.equals(cookie1.getName()))
                .findFirst();

        return cookie.map(Cookie::getValue)
                .orElse(null);
    }

    public Authentication getAuthentication(UserCredentialsDTO userCredentialsDTO) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userCredentialsDTO.getEmail(),
                        userCredentialsDTO.getPassword()));
    }
}
