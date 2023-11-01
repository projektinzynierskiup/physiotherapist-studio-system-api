package up.krakow.pchysioterapist.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.krakow.pchysioterapist.api.dto.AppointmentDTO;
import up.krakow.pchysioterapist.api.mapper.AppointmentMapper;
import up.krakow.pchysioterapist.api.service.AppointmentService;
import up.krakow.pchysioterapist.api.utils.ControllerEndpoints;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = ControllerEndpoints.MOD)
@AllArgsConstructor
public class ModController {

    private final AppointmentMapper appointmentMapper;

    private final AppointmentService appointmentService;

    @PostMapping("/appointment")
    ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO dto) {
        return ResponseEntity.ok(appointmentMapper.mapToAppointmentDTO(appointmentService.createAppointment(dto)));
    }

    @PostMapping("/appointment/all-day")
    ResponseEntity<List<AppointmentDTO>> addAppointmentDate(@RequestParam("startDate") LocalDateTime startDate,
                                                            @RequestParam("endDate") LocalDateTime endDate) {
        return ResponseEntity.ok(appointmentMapper.mapAppointmentListToAppointmentDTOList(appointmentService.createAppointmentsForDay(startDate, endDate)));
    }

    @GetMapping("/appointment/{appointmentId}")
    ResponseEntity<AppointmentDTO> getAppointment(@PathVariable Integer appointmentId) {
        return ResponseEntity.ok(appointmentMapper.mapToAppointmentDTO(appointmentService.getAppointment(appointmentId)));
    }

    @PutMapping("/appointment/{appointmentId}")
    ResponseEntity<AppointmentDTO> updateAppointment(@RequestBody AppointmentDTO dto, @PathVariable Integer appointmentId) {
        return ResponseEntity.ok(appointmentMapper.mapToAppointmentDTO(appointmentService.editAppointment(dto, appointmentId)));
    }

    @DeleteMapping("/appointment/{appointmentId}")
    void deleteAppointment(@PathVariable Integer appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
    }
}
