package up.krakow.pchysioterapist.api.service;

import up.krakow.pchysioterapist.api.dto.MassageDTO;
import up.krakow.pchysioterapist.api.model.Massage;

import java.util.List;

public interface MassageService {

    Massage getMassageById(Integer massageId);
    void save(MassageDTO massageDTO);
    List<MassageDTO> getAll();
    void delete(Integer id);
    void update(MassageDTO massageDTO);
}
