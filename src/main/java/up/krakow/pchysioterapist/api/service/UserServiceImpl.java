package up.krakow.pchysioterapist.api.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import up.krakow.pchysioterapist.api.dto.UserCredentialsDTO;
import up.krakow.pchysioterapist.api.dto.UsersDTO;
import up.krakow.pchysioterapist.api.exception.BadPasswordException;
import up.krakow.pchysioterapist.api.exception.UserExistsException;
import up.krakow.pchysioterapist.api.model.Users;
import up.krakow.pchysioterapist.api.model.enums.ERole;
import up.krakow.pchysioterapist.api.repository.UsersRepository;
import up.krakow.pchysioterapist.api.utils.StringUtils;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public Users getUserByEmail(String email) {
        return usersRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Nie odnaleziono osoby o takim emailu"));
    }

    @Override
    public boolean isUserValid(UserCredentialsDTO userCredentialsDTO) {
        Users users = getUserByEmail(userCredentialsDTO.getEmail());
        return isPasswordValid(users.getPassword(), userCredentialsDTO.getPassword());
    }

    @Override
    public boolean isPasswordValid(String usersPassword, String dtoPassword) {
        if(bCryptPasswordEncoder.matches(dtoPassword, usersPassword)) {
            return true;
        }
        throw new BadPasswordException("Wprowadzono nieprawidłowe haslo");
    }

    @Transactional
    @Override
    public void save(UsersDTO usersDTO) {
        Users users = Users.builder()
                .email(usersDTO.getEmail().toLowerCase())
                .username(StringUtils.capitalizeFirstLetter(usersDTO.getUsername()))
                .surname(StringUtils.capitalizeFirstLetter(usersDTO.getSurname()))
                .password(bCryptPasswordEncoder.encode(usersDTO.getPassword()))
                .role(ERole.USER)
                .enabled(true)
                .build();

        usersRepository.save(users);
    }

    @Override
    public void registerUser(UsersDTO usersDTO) {
        try {
            Users users = getUserByEmail(usersDTO.getEmail());
            if(users != null)
                throw new UserExistsException("Użytkownik o takim emailu jest już w bazie");
        } catch (UsernameNotFoundException e) {
            save(usersDTO);
        }
    }

    @Override
    public Integer getIdByEmail(String email) {
        return usersRepository.findByEmail(email).get().getId();
    }

}
