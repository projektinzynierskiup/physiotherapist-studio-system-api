package up.krakow.pchysioterapist.api.exception;

public class UserIsSignedToNewsletterException extends RuntimeException{

    public UserIsSignedToNewsletterException(String msg){
        super(msg);
    }
}
