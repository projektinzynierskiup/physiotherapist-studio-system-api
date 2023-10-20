package up.krakow.pchysioterapist.api.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import up.krakow.pchysioterapist.api.config.jwt.JwtAuthenticationFilter;
import up.krakow.pchysioterapist.api.config.jwt.JwtUtils;
import up.krakow.pchysioterapist.api.repository.UsersRepository;
import up.krakow.pchysioterapist.api.service.CustomUserDetailsService;
import up.krakow.pchysioterapist.api.utils.ControllerEndpoints;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class Security {
    private final UsersRepository usersRepository;

    @Bean
    public SecurityFilterChain securityFilterChainOne(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(ControllerEndpoints.GUEST + "/**").permitAll()
                        .requestMatchers(ControllerEndpoints.USER + "/**").authenticated()
                        .requestMatchers(ControllerEndpoints.MOD + "/**").authenticated()
                        .requestMatchers(ControllerEndpoints.ADMIN + "/**").authenticated()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JwtAuthenticationFilter(
                        new JwtUtils(),
                        new CustomUserDetailsService(usersRepository)), UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(CustomUserDetailsService userDetailsService) {

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public UserDetailsService userDetailsService(UsersRepository usersRepository) {
        return usersRepository::findByUsername;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}