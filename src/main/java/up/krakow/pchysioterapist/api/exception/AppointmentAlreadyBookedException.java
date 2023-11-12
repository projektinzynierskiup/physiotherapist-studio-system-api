package up.krakow.pchysioterapist.api.exception;

public class AppointmentAlreadyBookedException extends RuntimeException {

    public AppointmentAlreadyBookedException(String msg){
        super(msg);
    }
}
