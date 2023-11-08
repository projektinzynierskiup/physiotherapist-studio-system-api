package up.krakow.pchysioterapist.api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import up.krakow.pchysioterapist.api.dto.AppointmentDTO;
import up.krakow.pchysioterapist.api.dto.CalendarDTO;
import up.krakow.pchysioterapist.api.dto.UsersDTO;
import up.krakow.pchysioterapist.api.exception.DatesException;
import up.krakow.pchysioterapist.api.mapper.MassageMapper;
import up.krakow.pchysioterapist.api.mapper.UsersMapper;
import up.krakow.pchysioterapist.api.model.Appointment;
import up.krakow.pchysioterapist.api.model.enums.EAppointmentStatus;
import up.krakow.pchysioterapist.api.repository.AppointmentRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
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
        appointment.setMassage(dto.getMassage());
        appointment.setStatus(dto.getStatus());
        appointment.setUsers(dto.getUser());
        appointment.setUrlKey(UUID.randomUUID().toString());
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Override
    public Appointment getAppointment(Integer appointmentId) {
        return appointmentRepository.findByAppointmentId(appointmentId).orElseThrow(() ->
                new NoSuchElementException("Appointment with id: " + appointmentId + "does not exist!"));
    }

    @Override
    public List<Appointment> getAllFreeAppointments() {
        return appointmentRepository.findAllByStatus(String.valueOf(EAppointmentStatus.FREE));
    }

    @Override
    public Appointment editAppointment(AppointmentDTO dto, Integer appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() ->
                new NoSuchElementException("Appointment with id: " + appointmentId + "does not exist!"));
        appointment.setStartDate(dto.getStartDate());
        appointment.setEndDate(dto.getEndDate());
        appointment.setMassage(dto.getMassage());
        appointment.setUsers(dto.getUser());
        appointment.setStatus(dto.getStatus());
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Override
    public Appointment bookAppointment(Integer appointmentId, AppointmentDTO dto) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() ->
                new NoSuchElementException("Appointment with id: " + appointmentId + "does not exist!"));
        appointment.setStatus(String.valueOf(EAppointmentStatus.BOOKED));
        appointment.setUsers(dto.getUser());
        appointment.setMassage(dto.getMassage());
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Override
    public void deleteAppointment(Integer appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }

    @Override
    public Appointment cancelAppointment(Integer appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() ->
                new NoSuchElementException("Appointment with id: " + appointmentId + "does not exist!"));
        appointment.setStatus(String.valueOf(EAppointmentStatus.FREE));
        appointment.setUsers(null);
        appointment.setMassage(null);
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Override
    public List<Appointment> findByStartDateBetweenOrderByStartDateAsc(LocalDateTime startDate) {
        return appointmentRepository.findByStartDateBetweenOrderByStartDateAsc(startDate, startDate.plusDays(7));
    }



    @Override
    public List<CalendarDTO> getWeeklyCalendar(LocalDateTime startDate) {
        return findByStartDateBetweenOrderByStartDateAsc(startDate).stream()
                .collect(Collectors.groupingBy(Appointment::getStartDate))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> {
                    LocalDateTime date = LocalDateTime.from(LocalDate.from(entry.getKey()));
                    List<UsersDTO> usersDTOList = entry.getValue().stream()
                            .map(app -> {
                                UsersDTO usersDTO = usersMapper.mapUsersToUsersDTO(app.getUsers());
                                usersDTO.setLocalTime(app.getStartDate().toLocalTime());
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

    @Override
    public List<Appointment> createAppointmentsForDay(LocalDateTime startDate, LocalDateTime endDate) {
        List<Appointment> appointments = new ArrayList<>();
    if (startDate.getDayOfMonth() == endDate.getDayOfMonth()) {
        int hours = endDate.getHour() - startDate.getHour();
        for (int i = 0; i<hours; i++){
            if (i == 0)
            {
                startDate = startDate.plusHours(0);
            } else {
                startDate = startDate.plusHours(1);
            }
            endDate = startDate.plusHours(1);
            Appointment appointment = new Appointment();
            appointment.setStartDate(startDate);
            appointment.setEndDate(endDate);
            appointment.setStatus(String.valueOf(EAppointmentStatus.FREE));
            appointments.add(appointment);
            appointmentRepository.save(appointment);
        }
    } else throw new DatesException("Wybrane daty muszą być w tym samym dniu!");
    return appointments;
    }

}
