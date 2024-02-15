package com.feras.task.management.service;

import com.feras.task.management.config.JwtUtil;
import com.feras.task.management.dto.user.AuthenticationResponseBody;
import com.feras.task.management.dto.user.UserRequestBody;
import com.feras.task.management.exception.common.exceptions.InvalidDataException;
import com.feras.task.management.exception.user.exceptions.UserAlreadyExistException;
import com.feras.task.management.exception.user.exceptions.UserNotFoundException;
import com.feras.task.management.model.User;
import com.feras.task.management.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void testRegister_Success() {
        UserRequestBody request = new UserRequestBody("username", "password");
        when(userRepository.existsByUsername(request.getUsername())).thenReturn(false);
        userService.register(request);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegister_InvalidDataException_EmptyUsername() {
        UserRequestBody request = new UserRequestBody(null, "password");
        assertThrows(InvalidDataException.class, () -> userService.register(request));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testRegister_InvalidDataException_EmptyPassword() {
        UserRequestBody request = new UserRequestBody("username", "       ");
        assertThrows(InvalidDataException.class, () -> userService.register(request));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testRegister_UserAlreadyExistException() {
        UserRequestBody request = new UserRequestBody("username", "password");
        when(userRepository.existsByUsername(request.getUsername())).thenReturn(true);
        assertThrows(UserAlreadyExistException.class, () -> userService.register(request));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testAuthenticate_Success() {
        UserRequestBody request = new UserRequestBody("username", "password");
        User user = User.builder()
                .id(1L)
                .username("username")
                .password("password")
                .build();

        when(userRepository.findByUsername(request.getUsername())).thenReturn(Optional.of(user));
        when(jwtUtil.generateToken(user.getUsername())).thenReturn("jwtToken");

        AuthenticationResponseBody response = userService.authenticate(request);

        assertNotNull(response);
        assertEquals("jwtToken", response.getToken());
    }

    @Test
    void testAuthenticate_InvalidDataException_EmptyUsername() {
        UserRequestBody request = new UserRequestBody(null, "password");
        assertThrows(InvalidDataException.class, () -> userService.authenticate(request));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testAuthenticate_InvalidDataException_EmptyPassword() {
        UserRequestBody request = new UserRequestBody("username", "");
        assertThrows(InvalidDataException.class, () -> userService.authenticate(request));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testAuthenticate_UserNotFoundException() {
        UserRequestBody request = new UserRequestBody("username", "password");
        when(userRepository.findByUsername(request.getUsername())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.authenticate(request));
    }

    @Test
    void testAuthenticate_AuthenticationFailed() {
        UserRequestBody request = new UserRequestBody("username", "password");
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new RuntimeException("Authentication failed"));

        assertThrows(RuntimeException.class, () -> userService.authenticate(request));
    }
}
