package up.krakow.pchysioterapist.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.krakow.pchysioterapist.api.dto.AppointmentDTO;
import up.krakow.pchysioterapist.api.mapper.AppointmentMapper;
import up.krakow.pchysioterapist.api.service.AppointmentService;
import up.krakow.pchysioterapist.api.utils.ControllerEndpoints;

import java.util.UUID;

@RestController
@RequestMapping(value = ControllerEndpoints.GUEST + "/appointment")
@AllArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    private AppointmentMapper appointmentMapper;

    @PostMapping
    ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO dto) {
        return ResponseEntity.ok(appointmentMapper.mapToAppointmentDTO(appointmentService.createAppointment(dto)));
    }

    @GetMapping("/{appointmentId}")
    ResponseEntity<AppointmentDTO> getAppointment(@PathVariable Integer appointmentId) {
        return ResponseEntity.ok(appointmentMapper.mapToAppointmentDTO(appointmentService.getAppointment(appointmentId)));
    }

    @PutMapping("/{appointmentId}")
    ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO dto, @PathVariable Integer appointmentId) {
        return ResponseEntity.ok(appointmentMapper.mapToAppointmentDTO(appointmentService.editAppointment(dto, appointmentId)));
    }

    @DeleteMapping("/{appointmentId}")
    void deleteAppointment(@PathVariable Integer appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
    }

}
