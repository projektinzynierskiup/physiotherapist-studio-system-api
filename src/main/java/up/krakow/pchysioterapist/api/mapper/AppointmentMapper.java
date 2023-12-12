package up.krakow.pchysioterapist.api.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import up.krakow.pchysioterapist.api.dto.AppointmentDTO;
import up.krakow.pchysioterapist.api.dto.SimpleAppointmentDTO;
import up.krakow.pchysioterapist.api.model.Appointment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mapping(target = "users.id", source="userId")
    @Mapping(target = "massage.id",source = "massageId")
    Appointment mapToAppointment(AppointmentDTO dto);

    @InheritInverseConfiguration(name = "mapToAppointment")
    AppointmentDTO mapToAppointmentDTO(Appointment appointment);

    List<AppointmentDTO> mapAppointmentListToAppointmentDTOList(List<Appointment> appointmentList);
    @Mapping(target = "id")
    @Mapping(target = "startDate")
    @Mapping(target = "endDate")
    List<SimpleAppointmentDTO> mapToSimpleAppointmentDTOList(List<Appointment> appointmentList);
}
