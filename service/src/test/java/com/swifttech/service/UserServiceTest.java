package com.swifttech.service;


import com.swifttech.entity.User;
import com.swifttech.entity.dto.response.UserResponseCustomDTO;
import com.swifttech.entity.enumuration.Role;
import com.swifttech.repository.UserRepository;
import com.swifttech.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;


    @InjectMocks
    UserServiceImpl userService;


    @Test
    void editUser() {
        User mockUser = User.builder()
                .id(1L)
                .firstName("Test2")
                .middleName("user")
                .lastName("one")
                .email("testUserOne1@gmail.com")
                .role(Role.ADMIN)
                .build();

        when(userRepository.findById(mockUser.getId())).thenReturn(Optional.of(mockUser));
        when(userRepository.save(Mockito.any(User.class))).thenReturn(mockUser);

        User editedUser = userService.editUser(mockUser);

        verify(userRepository).findById(mockUser.getId());

        verify(userRepository).save(argThat(savedUser -> savedUser.equals(mockUser)));

        assertEquals(mockUser.getFirstName(), editedUser.getFirstName());
        assertEquals(mockUser.getEmail(), editedUser.getEmail());


    }

    @Test
    void getUserByUserCode() {

//        UUID userCode = UUID.fromString("313f8acd-660e-4383-8991-ba864f80c2ae");
//        UserResponseCustomDTO mockDto = UserResponseCustomDTO.builder()
//                .id(1L)
//                .firstName("Ashim")
//                .userCode(userCode)
//                .build();
//
//        UserResponseCustomDTO customDTO = userService.getUserByUserCode(userCode);
//        assertEquals(mockDto.getFirstName(), customDTO.getFirstName());

//        UUID userCode = UUID.fromString("313f8acd-660e-4383-8991-ba864f80c2ae");
//        userService.getUserByUserCode(userCode);
//        verify(userRepository, times(1)).findByUserCode(userCode);
    }


}
