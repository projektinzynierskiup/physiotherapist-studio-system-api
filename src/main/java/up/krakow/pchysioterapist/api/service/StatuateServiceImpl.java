package up.krakow.pchysioterapist.api.service;

import org.hibernate.tool.schema.internal.StandardTableCleaner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import up.krakow.pchysioterapist.api.dto.StatuateDTO;
import up.krakow.pchysioterapist.api.exception.StatuateException;
import up.krakow.pchysioterapist.api.mapper.StatuateMapper;
import up.krakow.pchysioterapist.api.model.Statuate;
import up.krakow.pchysioterapist.api.repository.StatuateRepository;

@Service
public class StatuateServiceImpl implements StatuateService{
    @Autowired
    private StatuateRepository statuateRepository;
    @Autowired
    private StatuateMapper statuateMapper;
    @Override
    public StatuateDTO getStatuate() {
        if(statuateRepository.findAll().size() == 0)
            throw new StatuateException("Regulamin na stronie nie istnieje.");
        return statuateMapper.mapStatuateToStatuateDTO(statuateRepository.findById(1).get());
    }

    @Override
    public void changeStatute(StatuateDTO statuateDTO) {
        if(statuateRepository.findAll().size() == 0)
            throw new StatuateException("Regulamin na stronie nie istnieje.");
        Statuate statuate = statuateRepository.findById(statuateDTO.getId()).get();

        if(statuate == null)
            throw new StatuateException("Złe id");
        statuate.setContent(statuateDTO.getContent());
        save(statuate);
    }

    @Override
    public void createStatuate(StatuateDTO statuateDTO) {
        if(statuateRepository.findAll().size() != 0)
            throw new StatuateException("Regulamin na stronie juz istnieje.");
        Statuate statuate = Statuate.builder()
                .content(statuateDTO.getContent())
                .build();
        save(statuate);
    }

    @Override
    public void deleteStatuate(Integer id) {
        if(statuateRepository.findAll().size() == 0)
            throw new StatuateException("Regulamin na stronie nie istnieje.");
        Statuate statuate = statuateRepository.findById(id).get();
        statuateRepository.delete(statuate);
    }

    @Override
    public void save(Statuate statuate) {
        statuateRepository.save(statuate);
    }
}
