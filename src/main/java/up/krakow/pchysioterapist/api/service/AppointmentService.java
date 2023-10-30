package up.krakow.pchysioterapist.api.service;

import up.krakow.pchysioterapist.api.dto.AppointmentDTO;
import up.krakow.pchysioterapist.api.dto.CalendarDTO;
import up.krakow.pchysioterapist.api.model.Appointment;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AppointmentService {

    Appointment createAppointment(AppointmentDTO dto);

    Appointment getAppointment(Integer appointmentId);

    Appointment editAppointment(AppointmentDTO dto, Integer appointmentId);

    void deleteAppointment(Integer appointmentId);
    List<Appointment> findByStartDateBetweenOrderByStartDateAsc(LocalDate startDate);
    List<CalendarDTO> getWeeklycalendar(LocalDate startDate);
}
