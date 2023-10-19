package up.krakow.pchysioterapist.api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import up.krakow.pchysioterapist.api.dto.AppointmentDTO;
import up.krakow.pchysioterapist.api.model.Appointment;
import up.krakow.pchysioterapist.api.repository.AppointmentRepository;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Override
    public Appointment createAppointment(AppointmentDTO dto) {
        Appointment appointment = new Appointment();
        appointment.setStartDate(dto.getStartDate());
        appointment.setEndDate(dto.getEndDate());
        appointment.setType(dto.getType());
        appointment.setUserId(dto.getUserId());
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Override
    public Appointment getAppointment(UUID appointmentId) {
        return appointmentRepository.findById(appointmentId).orElseThrow(() ->
                new NoSuchElementException("Appointment with id: " + appointmentId + "does not exist!"));
    }

    @Override
    public Appointment editAppointment(AppointmentDTO dto, UUID appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() ->
                new NoSuchElementException("Appointment with id: " + appointmentId + "does not exist!"));
        appointment.setStartDate(dto.getStartDate());
        appointment.setEndDate(dto.getEndDate());
        appointment.setType(dto.getType());
        appointment.setUserId(dto.getUserId());
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Override
    public void deleteAppointment(UUID appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }
}
