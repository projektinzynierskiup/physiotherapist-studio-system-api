package up.krakow.pchysioterapist.api.exception;

public class BadEmailTypeRequestException extends RuntimeException{
    public BadEmailTypeRequestException(String message) {
        super(message);
    }
}
