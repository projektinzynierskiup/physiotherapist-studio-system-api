package up.krakow.pchysioterapist.api.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import up.krakow.pchysioterapist.api.dto.OpinionDTO;
import up.krakow.pchysioterapist.api.mapper.OpinionMapper;
import up.krakow.pchysioterapist.api.model.Opinion;
import up.krakow.pchysioterapist.api.repository.OpinionRepository;

import java.time.LocalDate;

@Service
public class OpinionServiceImpl implements OpinionService{
    @Autowired
    private OpinionMapper opinionMapper;
    @Autowired
    private OpinionRepository opinionRepository;
    @Transactional
    @Override
    public void save(OpinionDTO opinionDTO) {
        Opinion opinion = opinionMapper.opinionDTOToOpinion(opinionDTO);
        opinion.setLocalDate(LocalDate.now());
        opinionRepository.save(opinion);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Opinion opinion = opinionRepository.findById(id).get();
        opinionRepository.delete(opinion);
    }
}
