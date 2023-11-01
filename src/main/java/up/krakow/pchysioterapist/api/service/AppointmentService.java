package up.krakow.pchysioterapist.api.service;

import up.krakow.pchysioterapist.api.dto.AppointmentDTO;
import up.krakow.pchysioterapist.api.dto.CalendarDTO;
import up.krakow.pchysioterapist.api.model.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    Appointment createAppointment(AppointmentDTO dto);

    Appointment getAppointment(Integer appointmentId);

    Appointment editAppointment(AppointmentDTO dto, Integer appointmentId);

    void deleteAppointment(Integer appointmentId);
    List<Appointment> findByStartDateBetweenOrderByStartDateAsc(LocalDateTime startDate);
    List<CalendarDTO> getWeeklyCalendar(LocalDateTime startDate);
}
