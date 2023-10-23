package up.krakow.pchysioterapist.api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import up.krakow.pchysioterapist.api.dto.AppointmentDTO;
import up.krakow.pchysioterapist.api.model.Appointment;
import up.krakow.pchysioterapist.api.repository.AppointmentRepository;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Override
    public Appointment createAppointment(AppointmentDTO dto) {
        Appointment appointment = new Appointment();
        appointment.setStartDate(dto.getStartDate());
        appointment.setEndDate(dto.getEndDate());
//        appointment.setAppointmentType(dto.getType());
//        appointment.setUserId(dto.getUserId());
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
}
