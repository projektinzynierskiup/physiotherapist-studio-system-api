package up.krakow.pchysioterapist.api.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import up.krakow.pchysioterapist.api.dto.PasswordDTO;
import up.krakow.pchysioterapist.api.dto.UserCredentialsDTO;
import up.krakow.pchysioterapist.api.dto.UsersDTO;
import up.krakow.pchysioterapist.api.exception.BadPasswordException;
import up.krakow.pchysioterapist.api.exception.PasswordException;
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
    public void deleteAccount(Integer id) {
        Users users = getUserById(id);
        usersRepository.delete(users);
    }

    @Override
    public Users getGuestByEmail(String email) {
        return usersRepository.findGuestByEmail(email);
    }

    @Override
    public void updatePasswordForUser(PasswordDTO passwordDTO) {
        Users users = getUserById(passwordDTO.getId());
        if(bCryptPasswordEncoder.matches(passwordDTO.getOldPassword(), users.getPassword()) &&
                passwordDTO.getPassword().equals(passwordDTO.getRepeatPassword())) {
                users.setPassword(bCryptPasswordEncoder.encode(passwordDTO.getPassword()));
                saveUser(users);
        } else {
            throw new PasswordException("Hasła sa nieprawidłowe");
        }
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
        Users guestUser = getGuestByEmail(usersDTO.getEmail());
        if (guestUser != null){
            guestUser.setUsername(usersDTO.getUsername());
            guestUser.setSurname(usersDTO.getSurname());
            guestUser.setRole(ERole.USER);
            usersRepository.save(guestUser);
        } else {
            try {
                Users users = getUserByEmail(usersDTO.getEmail());
                if (users != null)
                    throw new UserExistsException("Użytkownik o takim emailu jest już w bazie");
            } catch (UsernameNotFoundException e) {
                saveNewUser(usersDTO);
            }
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

    @Override
    public void changeDetails(UsersDTO usersDTO) {
        Users users = Users.builder()
                .id(usersDTO.getId())
                .enabled(true)
                .role(ERole.USER)
                .surname(usersDTO.getSurname())
                .username(usersDTO.getUsername())
                .email(usersDTO.getEmail())
                .build();
        updateUserDate(users);
    }

    @Override
    public void saveUser(Users users) {
        usersRepository.save(users);
    }
}
