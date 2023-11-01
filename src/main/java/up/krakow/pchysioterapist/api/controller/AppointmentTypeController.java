package up.krakow.pchysioterapist.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import up.krakow.pchysioterapist.api.model.enums.EAppointmentType;
import up.krakow.pchysioterapist.api.utils.ControllerEndpoints;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(ControllerEndpoints.GUEST + ControllerEndpoints.EAPPOINTMENT_TYPE)
@AllArgsConstructor
public class AppointmentTypeController {
    @GetMapping(value = "/all")
    public ResponseEntity<List<EAppointmentType>> getAllEAppointmentTypes() {
        return ResponseEntity.ok(Arrays.asList(EAppointmentType.values()));
    }
}
