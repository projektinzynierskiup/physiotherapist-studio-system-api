package up.krakow.pchysioterapist.api.service;

import up.krakow.pchysioterapist.api.dto.UserCredentialsDTO;
import up.krakow.pchysioterapist.api.dto.UsersDTO;
import up.krakow.pchysioterapist.api.model.Users;

import java.util.UUID;

public interface UserService {
    Users getUserByEmail(String email);
    boolean isUserValid(UserCredentialsDTO userCredentialsDTO);
    boolean isPasswordValid(String usersPassword, String dtoPassword);
    void save(UsersDTO usersDTO);
    void registerUser(UsersDTO usersDTO) throws Exception;
    Integer getIdByEmail(String email);
}
