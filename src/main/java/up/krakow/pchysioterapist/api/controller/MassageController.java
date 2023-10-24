package up.krakow.pchysioterapist.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import up.krakow.pchysioterapist.api.dto.MassageDTO;
import up.krakow.pchysioterapist.api.mapper.MassageMapper;
import up.krakow.pchysioterapist.api.model.Massage;
import up.krakow.pchysioterapist.api.repository.MassageRepository;
import up.krakow.pchysioterapist.api.utils.ControllerEndpoints;

import java.util.List;

@RestController
@RequestMapping(value = ControllerEndpoints.GUEST + ControllerEndpoints.MASSAGE)
@AllArgsConstructor
public class MassageController {
    @Autowired
    private MassageRepository massageRepository;
    @Autowired
    private MassageMapper massageMapper;
    @GetMapping(value = "/all")
    ResponseEntity<List<MassageDTO>> getAll() {
        List<Massage> massageList = massageRepository.findAll();

        return ResponseEntity.ok(massageMapper.massageToMassageDTO(massageList));
    }
}
