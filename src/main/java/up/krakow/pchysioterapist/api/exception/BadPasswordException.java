package up.krakow.pchysioterapist.api.exception;

import org.springframework.security.core.AuthenticationException;

public class BadPasswordException extends AuthenticationException {
    public BadPasswordException(String message) {
        super(message);
    }
}
