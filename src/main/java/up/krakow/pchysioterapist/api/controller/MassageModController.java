package up.krakow.pchysioterapist.api.controller;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.krakow.pchysioterapist.api.dto.InfoDTO;
import up.krakow.pchysioterapist.api.dto.MassageDTO;
import up.krakow.pchysioterapist.api.model.enums.EAppointmentType;
import up.krakow.pchysioterapist.api.service.MassageService;
import up.krakow.pchysioterapist.api.utils.ControllerEndpoints;

import java.util.List;

@RestController
@RequestMapping(value = ControllerEndpoints.MOD + ControllerEndpoints.MASSAGE)
@AllArgsConstructor
public class MassageModController {
    private final MassageService massageService;
    @PostMapping
    public ResponseEntity<InfoDTO> createMassage(@RequestBody MassageDTO massageDTO) {
        massageService.save(massageDTO);
        return ResponseEntity.ok(new InfoDTO("Utworzono masaż"));
    }

    @GetMapping
    public ResponseEntity<List<MassageDTO>> getAllMassages() {
        return ResponseEntity.ok(massageService.getAll());
    }

    @DeleteMapping("/{massageId}")
    public ResponseEntity<InfoDTO> delete(@PathVariable Integer massageId) {
        massageService.delete(massageId);
        return ResponseEntity.ok(new InfoDTO("Usunięto massaż o id " + massageId));
    }

    @PutMapping
    public ResponseEntity<InfoDTO> update(@RequestBody MassageDTO massageDTO) {
        System.out.println(massageDTO.toString());
        massageService.update(massageDTO);
        return ResponseEntity.ok(new InfoDTO("Zmieniono"));
    }
}
