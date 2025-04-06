package com.ecommerce.domain.service;

import com.ecommerce.domain.security.JwtProvider;
import com.ecommerce.domain.valueobjects.Email;
import com.ecommerce.domain.dto.LoginResult;
import com.ecommerce.domain.entities.User;
import com.ecommerce.domain.exceptions.InvalidCredentialsException;
import com.ecommerce.domain.exceptions.UserNotFoundException;
import com.ecommerce.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtProvider jwtProvider;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginWithValidCredentials_ShouldReturnLoginResult() {

        Email email = new Email("test@domain.de");
        String password = "testPassword";

        User mockUser = new User();
        mockUser.setId(UUID.randomUUID());
        mockUser.setEmail(email);
        mockUser.setPassword(password);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(mockUser));
        when(jwtProvider.generateToken(anyString())).thenReturn("mocked-jwt-token");

        LoginResult loginResult = authService.login(email, password);

        assertNotNull(loginResult, "LoginResult should not be null.");
        assertEquals(mockUser, loginResult.getUser(), "Returned user should match the mock user.");
        assertEquals("mocked-jwt-token", loginResult.getToken(), "Token should match the mocked JWT token.");
    }

    @Test
    void testLoginWithUnknownUser_ShouldThrowUserNotFoundException() {

        Email email = new Email("unknown@domain.de");
        String password = "irrelevant";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> authService.login(email, password),
                "Should throw UserNotFoundException if user doesn't exist.");
    }

    @Test
    void testLoginWithInvalidPassword_ShouldThrowInvalidCredentialsException() {

        Email email = new Email("valid@domain.de");
        String correctPassword = "correctPassword";
        String wrongPassword = "wrongPassword";

        User mockUser = new User();
        mockUser.setId(UUID.randomUUID());
        mockUser.setEmail(email);
        mockUser.setPassword(correctPassword);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(mockUser));

        assertThrows(InvalidCredentialsException.class,
                () -> authService.login(email, wrongPassword),
                "Should throw InvalidCredentialsException if password doesn't match.");
    }

    @Test
    void testLoginWithNullEmail_ShouldFail() {
        Email email = null;
        String password = "somePassword";

        assertThrows(UserNotFoundException.class, () -> authService.login(email, password),
            "Should throw NullPointerException if email is null.");
    }
}
