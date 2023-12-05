package up.krakow.pchysioterapist.api.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import up.krakow.pchysioterapist.api.dto.MassageDTO;
import up.krakow.pchysioterapist.api.model.Massage;

import java.util.List;
import java.util.Set;

@Component
@Mapper(componentModel = "spring")
public interface MassageMapper {
    MassageDTO massageToMassageDTO(Massage massage);
    Massage massageDtoToMassage(MassageDTO massageDTO);
    List<MassageDTO> massageToMassageDTO(List<Massage> massage);
    Set<MassageDTO> massageToMassageDTOSet(Set<Massage> massage);
}
