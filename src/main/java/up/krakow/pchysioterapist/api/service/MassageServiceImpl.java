package up.krakow.pchysioterapist.api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import up.krakow.pchysioterapist.api.dto.MassageDTO;
import up.krakow.pchysioterapist.api.mapper.MassageMapper;
import up.krakow.pchysioterapist.api.model.Massage;
import up.krakow.pchysioterapist.api.repository.MassageRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MassageServiceImpl implements MassageService{

    private final MassageRepository massageRepository;
    private final MassageMapper massageMapper;
    @Override
    public Massage getMassageById(Integer massageId) {
        return massageRepository.findById(massageId).orElseThrow();
    }

    @Override
    public void save(MassageDTO massageDTO) {
        massageRepository.save(massageMapper.massageDtoToMassage(massageDTO));
    }

    @Override
    public List<MassageDTO> getAll() {
        return massageMapper.massageToMassageDTO(massageRepository.findAll());
    }

    @Override
    public void delete(Integer id) {
        massageRepository.delete(massageRepository.findById(id).get());
    }

    @Override
    public void update(MassageDTO massageDTO) {
        Massage massage = massageRepository.findById(massage.DTO.getId);

        massage.setMassageName(massageDTO.getMassageName());
        massage.setDescription(massageDTO.getDescription());
        massage.setAppointmentType(massageDTO.getAppointmentType());

        massageRepository.save(massage);
    }
}
