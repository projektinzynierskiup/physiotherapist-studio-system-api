package up.krakow.pchysioterapist.api.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import up.krakow.pchysioterapist.api.dto.TokenDTO;
import up.krakow.pchysioterapist.api.utils.ControllerEndpoints;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(ControllerEndpoints.GUEST + "/c80f2044-78ec-11ee-b962-0242ac120002")
public class CurrentDateController {
    @GetMapping
    public ResponseEntity<TokenDTO> getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return ResponseEntity.ok(new TokenDTO(sdf.format(new Date())));
    }
}
