package up.krakow.pchysioterapist.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import up.krakow.pchysioterapist.api.dto.InfoDTO;
import up.krakow.pchysioterapist.api.service.OpinionService;
import up.krakow.pchysioterapist.api.utils.ControllerEndpoints;

@RestController
@RequestMapping(ControllerEndpoints.MOD + ControllerEndpoints.OPINION)
@AllArgsConstructor
public class OpinionModController {
    private final OpinionService opinionService;
    @DeleteMapping("/{id}")
    public ResponseEntity<InfoDTO> delete(@PathVariable Integer id) {
        opinionService.delete(id);
        return ResponseEntity.ok(new InfoDTO("Usunięto opinie o ID: " + id));
    }
}
