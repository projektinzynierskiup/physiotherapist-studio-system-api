package up.krakow.pchysioterapist.api.exception;

public class LocalDateTimeValidationException extends RuntimeException{
    public LocalDateTimeValidationException(String message) {
        super(message);
    }
}
