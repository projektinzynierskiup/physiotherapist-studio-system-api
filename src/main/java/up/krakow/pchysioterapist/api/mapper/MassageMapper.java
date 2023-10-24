package up.krakow.pchysioterapist.api.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import up.krakow.pchysioterapist.api.dto.MassageDTO;
import up.krakow.pchysioterapist.api.model.Massage;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface MassageMapper {
    MassageDTO massageToMassageDTO(Massage massage);
    List<MassageDTO> massageToMassageDTO(List<Massage> massage);
}
