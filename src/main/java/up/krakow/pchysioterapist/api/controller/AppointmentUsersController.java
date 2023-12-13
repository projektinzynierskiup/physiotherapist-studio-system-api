package up.krakow.pchysioterapist.api.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import up.krakow.pchysioterapist.api.dto.AppointmentDTO;
import up.krakow.pchysioterapist.api.model.Appointment;
import up.krakow.pchysioterapist.api.repository.AppointmentRepository;
import up.krakow.pchysioterapist.api.service.AppointmentService;
import up.krakow.pchysioterapist.api.utils.ControllerEndpoints;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = ControllerEndpoints.USER + ControllerEndpoints.APPOINTMENT)
@AllArgsConstructor
public class AppointmentUsersController {
    private final AppointmentService appointmentService;
    private final AppointmentRepository appointmentRepository;
    @GetMapping("/past/{id}")
    public ResponseEntity<List<AppointmentDTO>> getUserAppointmentHistory(@PathVariable Integer id) {
        return ResponseEntity.ok(appointmentService.getHistory(id));
    }

    @GetMapping("/future/{id}")
    public ResponseEntity<List<AppointmentDTO>> getUserAppointmentFuture(@PathVariable Integer id) {
        return ResponseEntity.ok(appointmentService.getFuture(id));
    }
}
