package up.krakow.pchysioterapist.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import up.krakow.pchysioterapist.api.dto.OpinionDTO;
import up.krakow.pchysioterapist.api.mapper.OpinionMapper;
import up.krakow.pchysioterapist.api.model.Opinion;
import up.krakow.pchysioterapist.api.repository.OpinionRepository;

import java.time.LocalDate;
import java.util.Date;

@Service
public class OpinionServiceImpl implements OpinionService{
    @Autowired
    private OpinionMapper opinionMapper;
    @Autowired
    private OpinionRepository opinionRepository;
    @Override
    public void save(OpinionDTO opinionDTO) {
        Opinion opinion = opinionMapper.opinionDTOToOpinion(opinionDTO);
        opinion.setLocalDate(LocalDate.now());
        opinionRepository.save(opinion);
    }
}
