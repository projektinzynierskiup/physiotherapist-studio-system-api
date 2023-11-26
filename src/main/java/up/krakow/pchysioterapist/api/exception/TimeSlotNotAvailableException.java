package up.krakow.pchysioterapist.api.exception;

public class TimeSlotNotAvailableException extends RuntimeException{

    public TimeSlotNotAvailableException(String msg) {
        super(msg);
    }
}
