package up.krakow.pchysioterapist.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import up.krakow.pchysioterapist.api.dto.AppointmentDTO;
import up.krakow.pchysioterapist.api.model.Appointment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    @Mapping(target = "user", ignore = true)
    AppointmentDTO mapToAppointmentDTO(Appointment appointment);
    List<AppointmentDTO> mapAppointmentListToAppointmentDTOList(List<Appointment> appointmentList);
}
