package up.krakow.pchysioterapist.api.mapper;

import org.mapstruct.Mapper;
import up.krakow.pchysioterapist.api.dto.AppointmentDTO;
import up.krakow.pchysioterapist.api.model.Appointment;

@Mapper
public interface AppointmentMapper {

    Appointment mapToAppointment(AppointmentDTO dto);

    AppointmentDTO mapToAppointmentDTO(Appointment appointment);
}
