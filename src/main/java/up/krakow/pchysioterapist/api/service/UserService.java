package up.krakow.pchysioterapist.api.service;

import up.krakow.pchysioterapist.api.dto.UserCredentialsDTO;
import up.krakow.pchysioterapist.api.dto.UsersDTO;
import up.krakow.pchysioterapist.api.model.Users;

public interface UserService {
    Users getUserByEmail(String email);

    Users getGuestByEmail(String email);

    boolean isUserValid(UserCredentialsDTO userCredentialsDTO);
    boolean isPasswordValid(String usersPassword, String dtoPassword);
    void saveNewUser(UsersDTO usersDTO);
    void updateUserDate(Users users);
    void registerUser(UsersDTO usersDTO) throws Exception;
    void updatePassword(Integer uuid, String password);
    Users getUserById(Integer id);

    void saveUser(Users users);
}
