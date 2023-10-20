package up.krakow.pchysioterapist.api.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import up.krakow.pchysioterapist.api.dto.UserCredentialsDTO;
import up.krakow.pchysioterapist.api.dto.UsersDTO;
import up.krakow.pchysioterapist.api.entity.Roles;
import up.krakow.pchysioterapist.api.entity.Users;
import up.krakow.pchysioterapist.api.repository.UsersRepository;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public Users getUserByEmail(String email) {
        return usersRepository.findByEmail(email).get();
    }

    @Override
    public boolean isUserValid(UserCredentialsDTO userCredentialsDTO) {
        Users users = getUserByEmail(userCredentialsDTO.getEmail());
        System.out.println("email valid" + users.getEmail());
        return isPasswordValid(users.getPassword(), userCredentialsDTO.getPassword());
    }

    @Override
    public boolean isPasswordValid(String usersPassword, String dtoPassword) {
        if(bCryptPasswordEncoder.matches(dtoPassword, usersPassword)) {
            return true;
        }
        throw new UsernameNotFoundException("nie ma takiego hasla");
    }

    @Transactional
    @Override
    public void save(UsersDTO usersDTO) {
        Users users = Users.builder()
                .email(usersDTO.getEmail())
                .username(usersDTO.getUsername())
                .password(bCryptPasswordEncoder.encode(usersDTO.getPassword()))
                .role(Roles.USER)
                .build();

        usersRepository.save(users);
    }

}
