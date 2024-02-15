package com.feras.task.management.service;

import com.feras.task.management.config.JwtUtil;
import com.feras.task.management.dto.user.AuthenticationResponseBody;
import com.feras.task.management.dto.user.UserRequestBody;
import com.feras.task.management.exception.common.exceptions.InvalidDataException;
import com.feras.task.management.exception.user.exceptions.UserAlreadyExistException;
import com.feras.task.management.exception.user.exceptions.UserNotFoundException;
import com.feras.task.management.model.User;
import com.feras.task.management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Transactional
    public void register(UserRequestBody request) {
        if (request.isInValidData()) {
            throw new InvalidDataException();
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistException();
        }

        userRepository.save(User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build());
    }

    public AuthenticationResponseBody authenticate(UserRequestBody request) {
        if (request.isInValidData()) {
            throw new InvalidDataException();
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new UserNotFoundException(request.getUsername()));

        String jwtToken = jwtUtil.generateToken(user.getUsername());

        return new AuthenticationResponseBody(jwtToken);
    }
}
