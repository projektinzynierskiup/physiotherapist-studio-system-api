package up.krakow.pchysioterapist.api.mapper;

import org.mapstruct.Mapper;
import up.krakow.pchysioterapist.api.dto.StatisticsDTO;
import up.krakow.pchysioterapist.api.model.Statistics;

@Mapper(componentModel = "spring")
public interface StatisticsMapper {

    StatisticsDTO mapToStatisticsDTO(Statistics statistics);

    Statistics mapToStatistics(StatisticsDTO dto);
}
