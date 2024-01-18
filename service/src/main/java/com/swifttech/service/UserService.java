package com.swifttech.service;

import com.swifttech.entity.User;
import com.swifttech.entity.dto.response.UserResponseCustomDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<User> getAllUsers();

    User editUser(User user);

    User getLoggedUserDetails();

    UserResponseCustomDTO getUserByUserCode(UUID userCode);


}
