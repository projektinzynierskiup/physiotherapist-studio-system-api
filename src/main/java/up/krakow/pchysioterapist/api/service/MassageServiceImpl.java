package up.krakow.pchysioterapist.api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import up.krakow.pchysioterapist.api.dto.MassageDTO;
import up.krakow.pchysioterapist.api.model.Massage;
import up.krakow.pchysioterapist.api.repository.MassageRepository;

@Service
@AllArgsConstructor
public class MassageServiceImpl implements MassageService{

    private final MassageRepository massageRepository;

    @Override
    public Massage getMassageById(Integer massageId) {
        return massageRepository.findById(massageId).orElseThrow();
    }
}
