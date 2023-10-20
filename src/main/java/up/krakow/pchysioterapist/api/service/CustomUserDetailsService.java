package up.krakow.pchysioterapist.api.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import up.krakow.pchysioterapist.api.entity.Users;
import up.krakow.pchysioterapist.api.repository.UsersRepository;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = usersRepository.findByEmail(email).get();
        return Users.builder()
                .email(email)
                .username(users.getUsername())
                .password(users.getPassword())
                .role(users.getRole())
                .build();
    }
}
