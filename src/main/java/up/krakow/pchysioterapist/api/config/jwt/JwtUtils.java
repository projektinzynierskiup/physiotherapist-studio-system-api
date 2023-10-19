package up.krakow.pchysioterapist.api.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtUtils {
    public String generateToken(Authentication authentication){
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("roles", authentication.getAuthorities())
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

        List<Map<String, String>> authorities = (List<Map<String, String>>) claims.get("roles");

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
}
