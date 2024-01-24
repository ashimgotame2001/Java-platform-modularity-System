package com.swifttech.service.impl;


import com.swifttech.entity.User;
import com.swifttech.entity.dto.response.UserResponseCustomDTO;
import com.swifttech.entity.mapper.UserMapper;
import com.swifttech.exceptions.BaseException;
import com.swifttech.repository.UserRepository;
import com.swifttech.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User editUser(User user) {
        if (user.getId() == null) {
            throw new UsernameNotFoundException("Invalid User Id");
        }
        User optionalUser = userRepository.findById(user.getId()).get();
        if (Objects.isNull(optionalUser)) {
            throw new UsernameNotFoundException("User not found");
        }
        optionalUser.setFirstName(user.getFirstName());
        optionalUser.setMiddleName(user.getMiddleName());
        optionalUser.setLastName(user.getLastName());
        optionalUser.setProfileInfo(user.getProfileInfo());
        return this.userRepository.save(optionalUser);
    }

    @Override
    public User getLoggedUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        return this.userRepository.findByEmail(username).orElseThrow();
    }

    @Override
    public UserResponseCustomDTO getUserByUserCode(UUID userCode) {
        Optional<User> user = this.userRepository.findByUserCode(userCode);
        if (user.isEmpty()) {
            throw new BaseException("User Not found", 400, "Invalid user code");
        }
        return UserMapper.INSTANCE.toCustomDTO(user.get());
    }


}



