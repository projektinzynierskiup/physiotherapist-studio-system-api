package up.krakow.pchysioterapist.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import up.krakow.pchysioterapist.api.dto.OpinionDTO;
import up.krakow.pchysioterapist.api.model.Opinion;
import up.krakow.pchysioterapist.api.repository.OpinionRepository;
import up.krakow.pchysioterapist.api.service.OpinionService;
import up.krakow.pchysioterapist.api.utils.ControllerEndpoints;

@RestController
@AllArgsConstructor
@RequestMapping(ControllerEndpoints.GUEST + ControllerEndpoints.OPINION)
public class OpinionController {
    private final OpinionService opinionService;
    private final OpinionRepository opinionRepository;
    @PostMapping
    public ResponseEntity<String> createOpinion(@RequestBody @Valid OpinionDTO opinionDTO) {
        opinionService.save(opinionDTO);
        return ResponseEntity.ok("hii");
    }

    @GetMapping("/{page}")
    public Page<Opinion> getOpinions(@PathVariable Integer page) {
        return opinionRepository.findAll(PageRequest.of
                (page, 5, Sort.by(Sort.Direction.DESC, "id")));
    }
}
