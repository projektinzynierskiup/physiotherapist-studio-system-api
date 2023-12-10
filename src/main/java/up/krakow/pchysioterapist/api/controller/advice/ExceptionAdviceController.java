package up.krakow.pchysioterapist.api.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import up.krakow.pchysioterapist.api.dto.InfoDTO;
import up.krakow.pchysioterapist.api.exception.*;

@ControllerAdvice
public class ExceptionAdviceController {
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<InfoDTO> handleUsernameNotFoundException(UsernameNotFoundException e) {
        return ResponseHelper.response400(e.getMessage());
    }

    @ExceptionHandler(StatuateException.class)
    public ResponseEntity<InfoDTO> handleStatuateException(StatuateException e) {
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

    @ExceptionHandler(UserIsSignedToNewsletterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<InfoDTO> userIsSignedToNewsletterException(UserIsSignedToNewsletterException e) {
        return ResponseHelper.response400(e.getMessage());
    }

    @ExceptionHandler(EmailDoesNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<InfoDTO> emailDoesNotExistException(EmailDoesNotExistException e) {
        return ResponseHelper.response400(e.getMessage());
    }

    @ExceptionHandler(LocalDateTimeValidationException.class)
    public ResponseEntity<InfoDTO> validationError(LocalDateTimeValidationException e) {
        return ResponseHelper.response400(e.getMessage());
    }

    @ExceptionHandler(AppointmentAlreadyBookedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<InfoDTO> appointmentAlreadyBookedException(AppointmentAlreadyBookedException e) {
        return ResponseHelper.response400(e.getMessage());
    }

    @ExceptionHandler(TimeSlotNotAvailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<InfoDTO> timeSlotNotAvailableException(TimeSlotNotAvailableException e) {
        return ResponseHelper.response400(e.getMessage());
    }
}
