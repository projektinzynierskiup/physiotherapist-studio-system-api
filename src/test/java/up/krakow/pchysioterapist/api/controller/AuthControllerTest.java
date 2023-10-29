package up.krakow.pchysioterapist.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import up.krakow.pchysioterapist.api.config.jwt.JwtUtils;
import up.krakow.pchysioterapist.api.dto.UserCredentialsDTO;
import up.krakow.pchysioterapist.api.dto.UsersDTO;
import up.krakow.pchysioterapist.api.exception.BadPasswordException;
import up.krakow.pchysioterapist.api.exception.UserExistsException;
import up.krakow.pchysioterapist.api.service.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    @InjectMocks
    AuthController authController;

    @Mock
    UserService userService;

    @Mock
    JwtUtils jwtUtils;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    @DisplayName("AuthController - ")
    public void loginWithInvalidCredentials() {
        UserCredentialsDTO invalidCredentials = new UserCredentialsDTO("wrong@example.com", "wrongpassword");

        when(userService.isUserValid(invalidCredentials)).thenThrow(new BadPasswordException("Invalid Credentials"));

        assertThrows(RuntimeException.class, () -> {
            authController.login(invalidCredentials);
        });
    }

    @Test
    public void loginWithInvalidEmailFormat_ShouldReturnError() {
        UserCredentialsDTO invalidCredentials = new UserCredentialsDTO("invalidEmail", "password12");

        when(userService.isUserValid(invalidCredentials)).thenThrow(new UsernameNotFoundException("Invalid Email Format"));

        assertThrows(RuntimeException.class, () -> {
            authController.login(invalidCredentials);
        });
    }

    @Test
    public void registerWithNewUser_ShouldReturnOk() throws Exception {
        UsersDTO newUser = new UsersDTO("test@example.com", "TestUser", "TestSurname", "password12");

        doNothing().when(userService).registerUser(newUser);

        ResponseEntity<?> response = authController.register(newUser);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Zarejestrowano", response.getBody());
    }

    @Test
    public void registerWithExistingUser_ShouldThrowException() throws Exception {
        UsersDTO existingUser = new UsersDTO("existing@example.com", "ExistUser", "ExistSurname", "password12");

        doThrow(new UserExistsException("Użytkownik o takim emailu jest już w bazie")).when(userService).registerUser(existingUser);

        assertThrows(UserExistsException.class, () -> {
            authController.register(existingUser);
        });
    }

    @Test
    public void registerWithInvalidEmail_ShouldThrowException() throws Exception {
        UsersDTO invalidEmailUser = new UsersDTO("invalidEmail", "InvalidUser", "InvalidSurname", "password12");

        // Zakładamy, że serwis rzuci wyjątek związany z walidacją
        doThrow(new RuntimeException("Invalid Email Format")).when(userService).registerUser(invalidEmailUser);

        assertThrows(RuntimeException.class, () -> {
            authController.register(invalidEmailUser);
        });
    }

    @Test
    public void registerWithShortPassword_ShouldThrowException() throws Exception {
        UsersDTO shortPasswordUser = new UsersDTO("test@example.com", "ShortPassUser", "ShortPassSurname", "pass");

        // Zakładamy, że serwis rzuci wyjątek związany z walidacją
        doThrow(new RuntimeException("Password is too short")).when(userService).registerUser(shortPasswordUser);

        assertThrows(RuntimeException.class, () -> {
            authController.register(shortPasswordUser);
        });
    }
}