package up.krakow.pchysioterapist.api.service;

import org.hibernate.persister.entity.SingleTableEntityPersister;
import up.krakow.pchysioterapist.api.dto.*;
import up.krakow.pchysioterapist.api.model.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    Appointment createAppointment(AppointmentDTO dto);

    Appointment getAppointment(Integer appointmentId);

    List<AppointmentResponseDTO> getAllFreeAppointments();

    List<Appointment> getAllFinishedAppointments();

    Appointment editAppointment(AppointmentDTO dto, Integer appointmentId);

    Appointment setStatusToFinished(Integer appointmentId);

    Appointment bookAppointment(Integer appointmentId, AppointmentDTO dto);

    Appointment bookAppointmentAsGuest(Integer appointmentId, AppointmentWithEmailDTO dto);

    void deleteAppointment(Integer appointmentId);

    Appointment cancelAppointment(Integer appointmentId);
    List<Appointment> findByStartDateBetweenOrderByStartDateAsc(LocalDateTime startDate);
    List<CalendarDTO> getWeeklyCalendar(LocalDateTime startDate);

    Appointment creteAppointmentForDate(LocalDateTime startDate, LocalDateTime endDate);

    List<Appointment> createAppointmentsForDay(List<StartEndDateDTO> dto);
    List<AppointmentDTO> getHistory(Integer id);
    List<AppointmentDTO> getFuture(Integer id);
    List<AppointmentDTO> getUserAppointmentList(List<Appointment> list, Integer id);
}
