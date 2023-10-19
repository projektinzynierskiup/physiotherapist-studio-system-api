package up.krakow.pchysioterapist.api.service;

import up.krakow.pchysioterapist.api.dto.AppointmentDTO;
import up.krakow.pchysioterapist.api.model.Appointment;

import java.util.UUID;

public interface AppointmentService {

    Appointment createAppointment(AppointmentDTO dto);

    Appointment getAppointment(UUID appointmentId);

    Appointment editAppointment(AppointmentDTO dto, UUID appointmentId);

    void deleteAppointment(UUID appointmentId);
}
