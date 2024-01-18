package com.swifttech.service.impl;

import com.swifttech.config.JwtService;
import com.swifttech.entity.User;
import com.swifttech.exceptions.BaseException;
import com.swifttech.repository.UserRepository;
import com.swifttech.response.AuthenticationRequest;
import com.swifttech.response.AuthenticationResponse;
import com.swifttech.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final String BAD_CREDENTIALS_MESSAGE = "Bad credentials";
    private static final String INVALID_CREDENTIALS_MESSAGE = "Invalid credentials";
    private static final String LOGGED_IN_MESSAGE = "Successfully logged in";
    private static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    private static final String TOKEN_TYPE = "BEARER";


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isEmpty()) {
            throw new BaseException(
                    USER_NOT_FOUND,
                    HttpStatus.NOT_FOUND.value(),
                    BAD_CREDENTIALS_MESSAGE);
        }
        if (passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            String jwtToken = jwtService.generateToken(user.get());
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .message(LOGGED_IN_MESSAGE)
                    .httpStatus(HttpStatus.OK.value())
                    .tokenType(TOKEN_TYPE)
                    .build();
        } else {
            throw new BaseException(
                    INVALID_CREDENTIALS_MESSAGE,
                    HttpStatus.BAD_REQUEST.value(),
                    BAD_CREDENTIALS_MESSAGE);
        }
    }

    @Override
    public User addUser(User user) {
        user.setUserCode(UUID.randomUUID());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }
}
