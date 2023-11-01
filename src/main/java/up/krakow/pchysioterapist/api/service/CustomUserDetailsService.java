package up.krakow.pchysioterapist.api.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import up.krakow.pchysioterapist.api.model.Users;
import up.krakow.pchysioterapist.api.model.enums.ERole;
import up.krakow.pchysioterapist.api.repository.UsersRepository;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        Users users = usersRepository.findByEmailAndRoleNot(username, ERole.GUEST).get();
        return Users.builder()
                .username(users.getUsername())
                .password(users.getPassword())
                .role(users.getRole())
                .build();
    }
}
