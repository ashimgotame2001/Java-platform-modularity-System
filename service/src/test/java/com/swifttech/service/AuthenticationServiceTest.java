package com.swifttech.service;

import com.swifttech.config.JwtService;
import com.swifttech.entity.ProfileInfo;
import com.swifttech.entity.User;
import com.swifttech.entity.enumuration.Role;
import com.swifttech.repository.UserRepository;
import com.swifttech.response.AuthenticationRequest;
import com.swifttech.response.AuthenticationResponse;
import com.swifttech.service.impl.AuthenticationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    BCryptPasswordEncoder passwordEncoder;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    JwtService jwtService;

    @InjectMocks
    AuthenticationServiceImpl authService;

    @Test
    void authenticate_ValidCredentials_ReturnsAuthenticationResponse() {

        String email = "ashimgotame@gmail.com";
        String password = "securePassword1234";
        String encodedPassword = "$2a$08$De.ZPCkx/8p/FM8L.8NbK.9EcEl8tGWrB/gESw0mvQKT.Pd1pM1d.";
        User mockUser = new User(email, encodedPassword);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(mockUser));
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(false);

        Authentication mockAuthentication = Mockito.mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(mockAuthentication);

        when(jwtService.generateToken(mockUser)).thenReturn("mockJwtToken");

        AuthenticationResponse response = authService.authenticate(new AuthenticationRequest(email, password));

        assertNotNull(response);
        assertEquals("mockJwtToken", response.getToken());
        assertEquals("Successfully logged in", response.getMessage());
        assertEquals(200, response.getHttpStatus());
        assertEquals("BEARER", response.getTokenType());

    }

    @Test
    void register_User() {

        ProfileInfo profileInfo = ProfileInfo.builder()
                .address("test")
                .city("kathmandu")
                .panNo(1212121212)
                .country("nepal")
                .toll("panipokhari")
                .citizenshipNo(121212121)
                .nationalIdNo(42323)
                .district("kathmandu")
                .phoneNumber(128712876)
                .build();

        User mockUser = User.builder()
                .firstName("null")
                .middleName("user")
                .lastName("one")
                .email("testUserOne@gmail.com")
                .password("password")
                .role(Role.USER)
                .profileInfo(profileInfo)
                .build();

        when(passwordEncoder.encode(Mockito.anyString())).thenReturn("encodedPassword");
//        when(userRepository.save(Mockito.isNull())).thenThrow(new BaseException("Required field not found", 400, "Required field not found"));
        when(userRepository.save(Mockito.any(User.class))).thenReturn(mockUser);

        User user = authService.addUser(mockUser);

        assertEquals(mockUser.getFirstName(), user.getFirstName());
        assertEquals(mockUser.getEmail(), user.getEmail());

//        BaseException exception = Assertions.assertThrows(BaseException.class, () -> {
//            authService.addUser(mockUser);
//        });
//
//        Assertions.assertEquals(400, exception.getHttpStatus());

    }

}
