package up.krakow.pchysioterapist.api.controller;


import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.krakow.pchysioterapist.api.dto.AppointmentDTO;
import up.krakow.pchysioterapist.api.dto.CalendarDTO;
import up.krakow.pchysioterapist.api.dto.UsersDTO;
import up.krakow.pchysioterapist.api.mapper.AppointmentMapper;
import up.krakow.pchysioterapist.api.mapper.MassageMapper;
import up.krakow.pchysioterapist.api.mapper.UsersMapper;
import up.krakow.pchysioterapist.api.model.Appointment;
import up.krakow.pchysioterapist.api.repository.AppointmentRepository;
import up.krakow.pchysioterapist.api.repository.UsersRepository;
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
    private final UsersRepository usersRepository;
    @GetMapping("/{date}")
    public ResponseEntity<List<CalendarDTO>> getWeeklyCalendar(@PathVariable String date) {
        System.out.println(date);
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        System.out.println(localDate);
        List<Appointment> appointmentList = appointmentRepository.findByStartDateBetweenOrderByStartDateAsc(localDate, localDate.plusDays(7));
//        List<Appointment> appointmentList = appointmentRepository.findAll();
        System.out.println(appointmentList);

        Map<LocalDate, List<UsersDTO>> map = new HashMap<>();

        List<AppointmentDTO> appointmentDTOList = new ArrayList<>();

        for(Appointment e: appointmentList) {
            AppointmentDTO appointmentDto = appointmentMapper.mapToAppointmentDTO(e);
            appointmentDTOList.add(appointmentDto);
        }
        System.out.println(appointmentDTOList);

        for(Appointment e: appointmentList) {
            System.out.println(e.getUsers().getEmail());
            UsersDTO usersDTO = usersMapper.mapUsersToUsersDTO(e.getUsers());
            usersDTO.setLocalTime(e.getStartTime());
            usersDTO.setMassageDTO(massageMapper.massageToMassageDTO(e.getMassage()));
            if(map.containsKey(e.getStartDate())) {
                map.get(e.getStartDate()).add(usersDTO);
            } else {
                map.put(e.getStartDate(), new ArrayList<>(List.of(usersDTO)));
            }
        }

        CalendarDTO calendarDTO = new CalendarDTO();
        List<CalendarDTO> calendarDTOList = new ArrayList<>();
        for(LocalDate e: map.keySet()) {
            System.out.println("MAP: " +e);

            List<UsersDTO> usersDTOS = map.get(e);
            for(UsersDTO users: usersDTOS) {
                System.out.println(usersDTOS);
            }
            calendarDTOList.add(new CalendarDTO(e, map.get(e)));
        }

        return ResponseEntity.ok(calendarDTOList);
    }

    @GetMapping("/e/{date}")
    public ResponseEntity<Map<LocalDate, List<UsersDTO>>> getWeeklyCalendarMap(@PathVariable String date) {
        System.out.println(date);
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        System.out.println(localDate);
        List<Appointment> appointmentList = appointmentRepository.findByStartDateBetweenOrderByStartDateAsc(localDate, localDate.plusDays(7));
//        List<Appointment> appointmentList = appointmentRepository.findAll();
        System.out.println(appointmentList);

        Map<LocalDate, List<UsersDTO>> map = new HashMap<>();

        List<AppointmentDTO> appointmentDTOList = new ArrayList<>();

        for(Appointment e: appointmentList) {
            AppointmentDTO appointmentDto = appointmentMapper.mapToAppointmentDTO(e);
            appointmentDTOList.add(appointmentDto);
        }
        System.out.println(appointmentDTOList);

        for(Appointment e: appointmentList) {
            System.out.println(e.getUsers().getEmail());
            UsersDTO usersDTO = usersMapper.mapUsersToUsersDTO(e.getUsers());
            usersDTO.setLocalTime(e.getStartTime());
            usersDTO.setMassageDTO(massageMapper.massageToMassageDTO(e.getMassage()));
            if(map.containsKey(e.getStartDate())) {
                map.get(e.getStartDate()).add(usersDTO);
            } else {
                map.put(e.getStartDate(), new ArrayList<>(List.of(usersDTO)));
            }
        }

        CalendarDTO calendarDTO = new CalendarDTO();
        List<CalendarDTO> calendarDTOList = new ArrayList<>();
        Map<LocalDate, List<UsersDTO>> map1 = new LinkedHashMap<>();
        for(LocalDate e: map.keySet()) {
            System.out.println("MAP: " +e);

            List<UsersDTO> usersDTOS = map.get(e);
            for(UsersDTO users: usersDTOS) {
                System.out.println(usersDTOS);
            }
            calendarDTOList.add(new CalendarDTO(e, map.get(e)));
            map1.put(e, map.get(e));
        }

        return ResponseEntity.ok(map1);
    }

    @GetMapping
    public String git() {
        return "git";
    }
}
