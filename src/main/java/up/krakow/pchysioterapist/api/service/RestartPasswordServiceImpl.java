package up.krakow.pchysioterapist.api.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import up.krakow.pchysioterapist.api.exception.LocalDateTimeValidationException;
import up.krakow.pchysioterapist.api.model.RestartPassword;
import up.krakow.pchysioterapist.api.model.Users;
import up.krakow.pchysioterapist.api.repository.RestartPasswordRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RestartPasswordServiceImpl implements RestartPasswordService{
    private final UserService userService;
    private final RestartPasswordRepository restartPasswordRepository;
    @Transactional
    @Override
    public void save(RestartPassword restartPassword) {
        restartPasswordRepository.save(restartPassword);
    }

    @Override
    public RestartPassword restartPasswordValidation(String email) {
        Users users = userService.getUserByEmail(email);
        RestartPassword restartPassword = createEntity(users);
        save(restartPassword);
        return restartPassword;
    }

    @Override
    public RestartPassword createEntity(Users users) {
        return RestartPassword.builder()
                .uuid(String.valueOf(UUID.randomUUID()))
                .usersId(users.getId())
                .startLocalDateTime(LocalDateTime.now())
                .endLocalDateTime(LocalDateTime.now().plusMinutes(15))
                .build();
    }

    @Override
    public RestartPassword getRestartPassword(String uuid) {
        return restartPasswordRepository.findByUuid(uuid).orElseThrow(
                () -> new UsernameNotFoundException("Wystąpił błąd z uuid"));
    }

    @Override
    public Integer getUsersIdFromRestartPassword(String uuid) {
        return getRestartPassword(uuid).getUsersId();
    }

    @Override
    public void timeValidation(RestartPassword restartPassword) {
        if(isTimeBetween(restartPassword.getStartLocalDateTime(), restartPassword.getEndLocalDateTime()))
            throw new LocalDateTimeValidationException("Upłynął czas. Wprowadź prośbę utworzenia nowego hasła jeszcze raz");
    }

    @Override
    public boolean isTimeBetween(LocalDateTime start, LocalDateTime stop) {
        System.out.println(LocalDateTime.now().isBefore(stop) && LocalDateTime.now().isAfter(start));
        return !(LocalDateTime.now().isBefore(stop) && LocalDateTime.now().isAfter(start));
    }
}
