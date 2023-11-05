package up.krakow.pchysioterapist.api.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import up.krakow.pchysioterapist.api.dto.InfoDTO;
import up.krakow.pchysioterapist.api.exception.BadEmailTypeRequestException;
import up.krakow.pchysioterapist.api.exception.BadPasswordException;
import up.krakow.pchysioterapist.api.exception.UnauthorizedException;
import up.krakow.pchysioterapist.api.exception.UserExistsException;

@ControllerAdvice
public class ExceptionAdviceController {
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<InfoDTO> handleUsernameNotFoundException(UsernameNotFoundException e) {
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseHelper.response400(e.getMessage());
    }

    @ExceptionHandler(BadPasswordException.class)
    public ResponseEntity<InfoDTO> handleBadPasswordException(BadPasswordException e) {
        return ResponseHelper.response400(e.getMessage());
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<InfoDTO> handleUserExistsException(UserExistsException e) {
        return ResponseHelper.response400(e.getMessage());
    }

    @ExceptionHandler(BadEmailTypeRequestException.class)
    public ResponseEntity<InfoDTO> handleBadEmailTypeRequestException(BadEmailTypeRequestException e) {
        return ResponseHelper.response400(e.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401 status code
    public ResponseEntity<InfoDTO> handleUnauthorizedException(UnauthorizedException e) {
        return new ResponseEntity<>(new InfoDTO(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
