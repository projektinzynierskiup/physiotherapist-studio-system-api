package up.krakow.pchysioterapist.api.service;

import up.krakow.pchysioterapist.api.dto.StatuateDTO;
import up.krakow.pchysioterapist.api.model.Statuate;

public interface StatuateService {
    StatuateDTO getStatuate();
    void changeStatute(StatuateDTO statuateDTO);
    void createStatuate(StatuateDTO statuateDTO);
    void deleteStatuate(Integer id);
    void save(Statuate statuate);
}
