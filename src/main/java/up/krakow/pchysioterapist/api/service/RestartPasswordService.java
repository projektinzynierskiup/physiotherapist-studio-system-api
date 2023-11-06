package up.krakow.pchysioterapist.api.service;


import up.krakow.pchysioterapist.api.dto.RestartPasswordDTO;
import up.krakow.pchysioterapist.api.model.RestartPassword;
import up.krakow.pchysioterapist.api.model.Users;

import java.time.LocalDateTime;

public interface RestartPasswordService {
    void save(RestartPassword restartPassword);
    RestartPassword restartPasswordValidation(String email);
    RestartPassword createEntity(Users users);
    RestartPassword getRestartPassword(String uuid);
    Integer getUsersIdFromRestartPassword(String uuid);
    void timeValidation(RestartPassword restartPassword);
    boolean isTimeBetween(LocalDateTime start, LocalDateTime stop);
}
