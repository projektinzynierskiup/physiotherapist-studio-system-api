package up.krakow.pchysioterapist.api.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UsersDTOTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void validUserDTO_ShouldPassValidation() {
        UsersDTO user = UsersDTO.builder()
                .email("test@example.com")
                .username("username")
                .surname("surname")
                .password("password12")
                .build();
        Set<ConstraintViolation<UsersDTO>> violations = validator.validate(user);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void userDTOWithInvalidEmail_ShouldFailValidation() {
        UsersDTO user = UsersDTO.builder()
                .email("invalidEmail")
                .username("username")
                .surname("surname")
                .password("password12")
                .build();
        Set<ConstraintViolation<UsersDTO>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void userDTOWithShortUsername_ShouldFailValidation() {
        UsersDTO user = UsersDTO.builder()
                .email("test@example.com")
                .username("ab")
                .surname("surname")
                .password("password12")
                .build();
        Set<ConstraintViolation<UsersDTO>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }
}