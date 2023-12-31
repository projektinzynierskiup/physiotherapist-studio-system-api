package up.krakow.pchysioterapist.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.krakow.pchysioterapist.api.dto.AppointmentDTO;
import up.krakow.pchysioterapist.api.dto.InfoDTO;
import up.krakow.pchysioterapist.api.dto.NewsletterDTO;
import up.krakow.pchysioterapist.api.dto.StartEndDateDTO;
import up.krakow.pchysioterapist.api.mapper.AppointmentMapper;
import up.krakow.pchysioterapist.api.service.AppointmentService;
import up.krakow.pchysioterapist.api.service.NewsletterService;
import up.krakow.pchysioterapist.api.utils.ControllerEndpoints;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = ControllerEndpoints.MOD)
@AllArgsConstructor
public class ModController {

    private final AppointmentMapper appointmentMapper;

    private final AppointmentService appointmentService;
    private final NewsletterService newsletterService;

    @PostMapping("/appointment")
    ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO dto) {
        return ResponseEntity.ok(appointmentMapper.mapToAppointmentDTO(appointmentService.createAppointment(dto)));
    }

    @PostMapping("appointment/date")
    ResponseEntity<AppointmentDTO> createAppointmentForDate(@RequestBody StartEndDateDTO dto) {
        LocalDateTime start = LocalDateTime.parse(dto.getStartDate());
        LocalDateTime end = LocalDateTime.parse(dto.getEndDate());
        return ResponseEntity.ok(appointmentMapper.mapToAppointmentDTO(appointmentService.creteAppointmentForDate(start, end)));
    }

    @PostMapping("/appointment/list")
    ResponseEntity<List<AppointmentDTO>> addAppointmentDate(@RequestBody List<StartEndDateDTO> dto) {
        return ResponseEntity.ok(appointmentMapper.mapAppointmentListToAppointmentDTOList(appointmentService.createAppointmentsForDay(dto)));
    }

    @GetMapping("/appointment/{appointmentId}")
    ResponseEntity<AppointmentDTO> getAppointment(@PathVariable Integer appointmentId) {
        return ResponseEntity.ok(appointmentMapper.mapToAppointmentDTO(appointmentService.getAppointment(appointmentId)));
    }

    @GetMapping("/appointment/all/finished")
    ResponseEntity<List<AppointmentDTO>> getAllFinishedAppointments() {
        return ResponseEntity.ok(appointmentMapper.mapAppointmentListToAppointmentDTOList(appointmentService.getAllFinishedAppointments()));
    }

    @PutMapping("/appointment/{appointmentId}")
    ResponseEntity<AppointmentDTO> updateAppointment(@RequestBody AppointmentDTO dto, @PathVariable Integer appointmentId) {
        return ResponseEntity.ok(appointmentMapper.mapToAppointmentDTO(appointmentService.editAppointment(dto, appointmentId)));
    }

    @PutMapping("/appointment/{appointmentId}/finished")
    ResponseEntity<AppointmentDTO> setAppointmentStatusToFinished(@PathVariable Integer appointmentId) {
        return ResponseEntity.ok(appointmentMapper.mapToAppointmentDTO(appointmentService.setStatusToFinished(appointmentId)));
    }

    @DeleteMapping("/appointment/{appointmentId}")
    void deleteAppointment(@PathVariable Integer appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
    }

    @DeleteMapping("/newsletter")
    public ResponseEntity<InfoDTO> signOutFromNewsletter(@RequestBody NewsletterDTO dto) {
        newsletterService.signOutFromNewsletter(dto.getUserEmail());
        return ResponseEntity.ok(new InfoDTO("Wypisano z newslettera!"));
    }
}
