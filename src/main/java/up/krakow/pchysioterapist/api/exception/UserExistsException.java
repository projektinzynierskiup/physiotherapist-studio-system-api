package up.krakow.pchysioterapist.api.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserExistsException extends AuthenticationException {
    public UserExistsException(String msg) {
        super(msg);
    }
}
