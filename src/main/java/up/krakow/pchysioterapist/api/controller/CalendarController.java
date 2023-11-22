package up.krakow.pchysioterapist.api.controller;


import lombok.AllArgsConstructor;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(monday, dateFormatter);
        LocalDateTime localDateTime = LocalDateTime.of(date, LocalTime.MIDNIGHT);
        System.out.println(localDateTime);
        return ResponseEntity.ok(appointmentService.getWeeklyCalendar(localDateTime));
    }
}
