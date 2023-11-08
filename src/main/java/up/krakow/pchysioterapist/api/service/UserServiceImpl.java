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
import up.krakow.pchysioterapist.api.mapper.UsersMapper;
import up.krakow.pchysioterapist.api.model.Users;
import up.krakow.pchysioterapist.api.model.enums.ERole;
import up.krakow.pchysioterapist.api.repository.UsersRepository;
import up.krakow.pchysioterapist.api.utils.StringUtils;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UsersMapper usersMapper;
    @Override
    public Users getUserByEmail(String email) {
        return usersRepository.findByEmailAndRoleNot(email, ERole.GUEST).orElseThrow(
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
    public void saveNewUser(UsersDTO usersDTO) {
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

    @Transactional
    @Override
    public void updateUserDate(Users users) {
        usersRepository.save(users);
    }

    @Transactional
    @Override
    public void registerUser(UsersDTO usersDTO) {
        try {
            Users users = getUserByEmail(usersDTO.getEmail());
            if(users != null)
                throw new UserExistsException("Użytkownik o takim emailu jest już w bazie");
        } catch (UsernameNotFoundException e) {
            saveNewUser(usersDTO);
        }
    }

    @Override
    public void updatePassword(Integer usersId, String password) {
        Users users = getUserById(usersId);
        users.setPassword(bCryptPasswordEncoder.encode(password));
        updateUserDate(users);
    }

    @Override
    public Users getUserById(Integer id) {
        return usersRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("Nie odnaleziono osoby o takim emailu"));
    }
}
