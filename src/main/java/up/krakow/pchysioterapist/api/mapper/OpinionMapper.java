package up.krakow.pchysioterapist.api.mapper;

import org.mapstruct.Mapper;
import up.krakow.pchysioterapist.api.dto.OpinionDTO;
import up.krakow.pchysioterapist.api.model.Opinion;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OpinionMapper {
    OpinionDTO opinionToOpinionDTO(Opinion opinion);
    Opinion opinionDTOToOpinion(OpinionDTO opinion);
    List<OpinionDTO> opinionListToOpinionDTOList(List<Opinion> opinionnList);
}
