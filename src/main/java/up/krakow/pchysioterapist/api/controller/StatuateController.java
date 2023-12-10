package up.krakow.pchysioterapist.api.controller;


import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.krakow.pchysioterapist.api.dto.InfoDTO;
import up.krakow.pchysioterapist.api.dto.StatuateDTO;
import up.krakow.pchysioterapist.api.service.StatuateService;
import up.krakow.pchysioterapist.api.utils.ControllerEndpoints;

@RestController
@RequestMapping
@AllArgsConstructor
public class StatuateController {
    private final StatuateService statuateService;
    @GetMapping(value = ControllerEndpoints.GUEST + ControllerEndpoints.STATUATE)
    public ResponseEntity<StatuateDTO> getStatuate() {
        return ResponseEntity.ok(statuateService.getStatuate());
    }

    @DeleteMapping(value = ControllerEndpoints.MOD + ControllerEndpoints.STATUATE + "/{id}")
    public ResponseEntity<InfoDTO> deleteStatuate(@PathVariable Integer id) {
        statuateService.deleteStatuate(id);
        return ResponseEntity.ok(new InfoDTO("Usunięto regulamin"));
    }

    @PostMapping(value = ControllerEndpoints.MOD + ControllerEndpoints.STATUATE)
    public ResponseEntity<InfoDTO> createStatuate(@RequestBody StatuateDTO statuateDTO) {
        statuateService.createStatuate(statuateDTO);
        return ResponseEntity.ok(new InfoDTO("Utworzono regulamin"));
    }

    @PutMapping(value =  ControllerEndpoints.MOD + ControllerEndpoints.STATUATE)
    public ResponseEntity<InfoDTO> changeStauate(@RequestBody StatuateDTO statuateDTO) {
        statuateService.changeStatute(statuateDTO);
        return ResponseEntity.ok(new InfoDTO("Zmieniono regulamin"));
    }
}
