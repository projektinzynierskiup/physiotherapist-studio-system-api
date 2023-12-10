package up.krakow.pchysioterapist.api.mapper;

import org.mapstruct.Mapper;
import up.krakow.pchysioterapist.api.dto.StatuateDTO;
import up.krakow.pchysioterapist.api.model.Statuate;

@Mapper(componentModel = "spring")
public interface StatuateMapper {
    StatuateDTO mapStatuateToStatuateDTO(Statuate statuate);
    Statuate mapStatuateDTOToStatuate(StatuateDTO statuateDTO);
}
