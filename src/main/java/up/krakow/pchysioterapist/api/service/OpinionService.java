package up.krakow.pchysioterapist.api.service;

import up.krakow.pchysioterapist.api.dto.OpinionDTO;

public interface OpinionService {
    void save(OpinionDTO opinionDTO);
    void delete(Integer id);
}
