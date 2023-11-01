package up.krakow.pchysioterapist.api.controller;


import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.krakow.pchysioterapist.api.dto.CalendarDTO;
import up.krakow.pchysioterapist.api.mapper.AppointmentMapper;
import up.krakow.pchysioterapist.api.mapper.MassageMapper;
import up.krakow.pchysioterapist.api.mapper.UsersMapper;
import up.krakow.pchysioterapist.api.repository.AppointmentRepository;
import up.krakow.pchysioterapist.api.service.AppointmentServiceImpl;
import up.krakow.pchysioterapist.api.utils.ControllerEndpoints;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping(value = ControllerEndpoints.MOD + ControllerEndpoints.CALENDAR)
@AllArgsConstructor
public class CalendarController {
    private final AppointmentRepository appointmentRepository;
    private final MassageMapper massageMapper;
    private final UsersMapper usersMapper;
    private final AppointmentMapper appointmentMapper;
    private final AppointmentServiceImpl appointmentService;
    @GetMapping("/{monday}")
    public ResponseEntity<List<CalendarDTO>> getWeeklyCalendar(@PathVariable String monday) {
        return ResponseEntity.ok(appointmentService.getWeeklycalendar(LocalDate.parse(monday, DateTimeFormatter.ISO_DATE)));
    }
}
