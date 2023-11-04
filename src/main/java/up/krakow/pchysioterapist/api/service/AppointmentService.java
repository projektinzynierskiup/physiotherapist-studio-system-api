package up.krakow.pchysioterapist.api.service;

import up.krakow.pchysioterapist.api.dto.AppointmentDTO;
import up.krakow.pchysioterapist.api.dto.CalendarDTO;
import up.krakow.pchysioterapist.api.model.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    Appointment createAppointment(AppointmentDTO dto);

    Appointment getAppointment(Integer appointmentId);

    List<Appointment> getAllFreeAppointments();

    Appointment editAppointment(AppointmentDTO dto, Integer appointmentId);

    Appointment bookAppointment(Integer appointmentId, AppointmentDTO dto);

    void deleteAppointment(Integer appointmentId);

    Appointment cancelAppointment(Integer appointmentId);
    List<Appointment> findByStartDateBetweenOrderByStartDateAsc(LocalDateTime startDate);
    List<CalendarDTO> getWeeklyCalendar(LocalDateTime startDate);

    List<Appointment> createAppointmentsForDay(LocalDateTime startDate, LocalDateTime endDate);
}
