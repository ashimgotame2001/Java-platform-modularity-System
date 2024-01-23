package com.swifttech.repositoryTest;

import com.swifttech.entity.User;
import com.swifttech.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;


    @Test
    void findByEmail() {
        String mockEmail = "test@example.com";
        User mockUser = new User();
        mockUser.setEmail(mockEmail);

        when(userRepository.findByEmail(mockEmail)).thenReturn(Optional.of(mockUser));
        Optional<User> result = userRepository.findByEmail(mockUser.getEmail());

        assertEquals(mockUser, result.orElse(null));


    }

    @Test
    void findByUserCode() {

        UUID mockUserCode = UUID.randomUUID();
        User mockUser = new User();
        mockUser.setUserCode(mockUserCode);

        when(userRepository.findByUserCode(mockUserCode)).thenReturn(Optional.of(mockUser));

        Optional<User> result = userRepository.findByUserCode(mockUserCode);

        assertEquals(mockUser, result.get());
    }
}
