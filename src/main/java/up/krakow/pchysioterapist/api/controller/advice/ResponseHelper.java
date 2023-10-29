package up.krakow.pchysioterapist.api.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import up.krakow.pchysioterapist.api.dto.InfoDTO;

public class ResponseHelper {
    public static ResponseEntity<InfoDTO> response400(String e){
        return new ResponseEntity<>(new InfoDTO(e), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<InfoDTO> response200(String e) {
        return new ResponseEntity<>(new InfoDTO(e), HttpStatus.OK);
    }
}
