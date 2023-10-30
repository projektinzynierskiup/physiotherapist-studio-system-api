package up.krakow.pchysioterapist.api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import up.krakow.pchysioterapist.api.dto.AppointmentDTO;
import up.krakow.pchysioterapist.api.dto.CalendarDTO;
import up.krakow.pchysioterapist.api.dto.UsersDTO;
import up.krakow.pchysioterapist.api.mapper.MassageMapper;
import up.krakow.pchysioterapist.api.mapper.UsersMapper;
import up.krakow.pchysioterapist.api.model.Appointment;
import up.krakow.pchysioterapist.api.model.Massage;
import up.krakow.pchysioterapist.api.repository.AppointmentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UsersMapper usersMapper;
    private final MassageMapper massageMapper;

    @Override
    public Appointment createAppointment(AppointmentDTO dto) {
        Appointment appointment = new Appointment();
        appointment.setStartDate(dto.getStartDate());
        appointment.setEndDate(dto.getEndDate());
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Override
    public Appointment getAppointment(Integer appointmentId) {
        return appointmentRepository.findById(appointmentId).orElseThrow(() ->
                new NoSuchElementException("Appointment with id: " + appointmentId + "does not exist!"));
    }

    @Override
    public Appointment editAppointment(AppointmentDTO dto, Integer appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() ->
                new NoSuchElementException("Appointment with id: " + appointmentId + "does not exist!"));
        appointment.setStartDate(dto.getStartDate());
        appointment.setEndDate(dto.getEndDate());
//        appointment.setAppointmentType(dto.getType());
//        appointment.setUserId(dto.getUserId());
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Override
    public void deleteAppointment(Integer appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }

    @Override
    public List<Appointment> findByStartDateBetweenOrderByStartDateAsc(LocalDate startDate) {
        return appointmentRepository.findByStartDateBetweenOrderByStartDateAsc(startDate, startDate.plusDays(7));
    }



    @Override
    public List<CalendarDTO> getWeeklycalendar(LocalDate startDate) {
        return findByStartDateBetweenOrderByStartDateAsc(startDate).stream()
                .collect(Collectors.groupingBy(Appointment::getStartDate))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> {
                    LocalDate date = entry.getKey();
                    List<UsersDTO> usersDTOList = entry.getValue().stream()
                            .map(app -> {
                                UsersDTO usersDTO = usersMapper.mapUsersToUsersDTO(app.getUsers());
                                usersDTO.setLocalTime(app.getStartTime());
                                usersDTO.setMassageDTO(massageMapper.massageToMassageDTO(app.getMassage()));
                                return usersDTO;
                            })
                            .collect(Collectors.toList());

                    CalendarDTO calendarDTO = new CalendarDTO();
                    calendarDTO.setLocalDate(date);
                    calendarDTO.setUsersDTOList(usersDTOList);
                    return calendarDTO;
                })
                .collect(Collectors.toList());
    }

}
