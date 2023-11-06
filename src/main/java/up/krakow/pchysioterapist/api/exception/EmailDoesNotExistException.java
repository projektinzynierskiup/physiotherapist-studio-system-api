package up.krakow.pchysioterapist.api.exception;

public class EmailDoesNotExistException extends RuntimeException{

    public EmailDoesNotExistException(String msg) {
        super(msg);
    }
}
