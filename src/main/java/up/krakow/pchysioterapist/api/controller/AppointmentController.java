package up.krakow.pchysioterapist.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.krakow.pchysioterapist.api.dto.AppointmentDTO;
import up.krakow.pchysioterapist.api.mapper.AppointmentMapper;
import up.krakow.pchysioterapist.api.service.AppointmentService;
import up.krakow.pchysioterapist.api.utils.ControllerEndpoints;

import java.util.List;

@RestController
@RequestMapping(value = ControllerEndpoints.GUEST + "/appointment")
@AllArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    private AppointmentMapper appointmentMapper;

    @GetMapping("/{appointmentId}")
    ResponseEntity<AppointmentDTO> getAppointment(@PathVariable Integer appointmentId) {
        return ResponseEntity.ok(appointmentMapper.mapToAppointmentDTO(appointmentService.getAppointment(appointmentId)));
    }

    @GetMapping("/all/free")
    ResponseEntity<List<AppointmentDTO>> getAllFreeAppointments() {
        return ResponseEntity.ok(appointmentMapper.mapAppointmentListToAppointmentDTOList(appointmentService.getAllFreeAppointments()));
    }

    @PutMapping("/{appointmentId}/book")
    ResponseEntity<AppointmentDTO> bookAppointment(@PathVariable Integer appointmentId) {
        return ResponseEntity.ok(appointmentMapper.mapToAppointmentDTO(appointmentService.bookAppointment(appointmentId)));
    }

    @PutMapping("/{appointmentId}/cancel")
    ResponseEntity<AppointmentDTO> cancelAppointment(@PathVariable Integer appointmentId) {
        return ResponseEntity.ok(appointmentMapper.mapToAppointmentDTO(appointmentService.cancelAppointment(appointmentId)));
    }

}
