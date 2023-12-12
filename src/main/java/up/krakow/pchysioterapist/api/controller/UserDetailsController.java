package up.krakow.pchysioterapist.api.controller;

import lombok.AllArgsConstructor;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.krakow.pchysioterapist.api.dto.InfoDTO;
import up.krakow.pchysioterapist.api.dto.PasswordDTO;
import up.krakow.pchysioterapist.api.dto.UsersDTO;
import up.krakow.pchysioterapist.api.service.UserService;
import up.krakow.pchysioterapist.api.utils.ControllerEndpoints;

import javax.sound.sampled.DataLine;

@RestController
@RequestMapping(ControllerEndpoints.USER)
@AllArgsConstructor
public class UserDetailsController {
    private final UserService userService;
    @PutMapping
    public ResponseEntity<InfoDTO> changeDetails(@RequestBody UsersDTO usersDTO) {
        userService.changeDetails(usersDTO);
        return ResponseEntity.ok(new InfoDTO("Zmieniono dane"));
    }

    @PutMapping("/password")
    public ResponseEntity<InfoDTO> changePassowrd(@RequestBody PasswordDTO passwordDTO) {
        userService.updatePasswordForUser(passwordDTO);
        return ResponseEntity.ok(new InfoDTO("Zmieniono hasło"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<InfoDTO> deleteAccount(@PathVariable Integer id) {
        userService.deleteAccount(id);
        return ResponseEntity.ok(new InfoDTO("Zmieniono hasło"));
    }
}
