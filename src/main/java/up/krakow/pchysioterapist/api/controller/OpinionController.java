package up.krakow.pchysioterapist.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import up.krakow.pchysioterapist.api.dto.OpinionDTO;
import up.krakow.pchysioterapist.api.repository.OpinionRepository;
import up.krakow.pchysioterapist.api.service.OpinionService;
import up.krakow.pchysioterapist.api.utils.ControllerEndpoints;

@RestController
@AllArgsConstructor
@RequestMapping(ControllerEndpoints.GUEST + ControllerEndpoints.OPINION)
public class OpinionController {
    private final OpinionService opinionService;
    @PostMapping
    public ResponseEntity<String> createOpinion(@RequestBody @Valid OpinionDTO opinionDTO) {
        opinionService.save(opinionDTO);
        return ResponseEntity.ok("hii");
    }
}
