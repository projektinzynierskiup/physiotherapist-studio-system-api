package up.krakow.pchysioterapist.api.controller;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.krakow.pchysioterapist.api.dto.InfoDTO;
import up.krakow.pchysioterapist.api.dto.RestartPasswordDTO;
import up.krakow.pchysioterapist.api.model.RestartPassword;
import up.krakow.pchysioterapist.api.repository.RestartPasswordRepository;
import up.krakow.pchysioterapist.api.service.EmailService;
import up.krakow.pchysioterapist.api.service.RestartPasswordService;
import up.krakow.pchysioterapist.api.service.UserService;
import up.krakow.pchysioterapist.api.utils.ControllerEndpoints;

@RequestMapping(ControllerEndpoints.GUEST + ControllerEndpoints.USERS)
@RestController
@AllArgsConstructor
public class UsersController {
    private final RestartPasswordService restartPasswordService;
    private final UserService userService;
    private final EmailService emailService;
    @PostMapping(value = "/restartpassword/{email}")
    public ResponseEntity<InfoDTO> restartPasssword(@PathVariable String email) throws MessagingException {
        RestartPassword restartPassword = restartPasswordService.restartPasswordValidation(email);
        emailService.restartPassworEmail(email, restartPassword);
        return ResponseEntity.ok(new InfoDTO("W celu zresetowania swojego hasla sprawdz email"));
    }

    @GetMapping(value = "/restartpassword/{uuid}")
    public ResponseEntity<InfoDTO> getRestartPassword(@PathVariable String uuid) {
        restartPasswordService.timeValidation(restartPasswordService.getRestartPassword(uuid));
        return ResponseEntity.ok(new InfoDTO(uuid));
    }

    @PutMapping(value = "/restartpassword")
    public ResponseEntity<InfoDTO> restart(@RequestBody @Valid RestartPasswordDTO restartPasswordDTO) {
        userService.updatePassword(
                restartPasswordService.getUsersIdFromRestartPassword(restartPasswordDTO.getUuid()),
                restartPasswordDTO.getPassword());
        return ResponseEntity.ok(new InfoDTO("Zmieniono haslo"));
    }
}
