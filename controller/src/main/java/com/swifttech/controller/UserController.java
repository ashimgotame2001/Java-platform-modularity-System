package com.swifttech.controller;

import com.swifttech.entity.User;
import com.swifttech.response.BaseResponse;
import com.swifttech.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/all")
    public BaseResponse getAllUsers() {
        return BaseResponse.builder()
                .data(userService.getAllUsers())
                .message("User List")
                .httpStatus(HttpStatus.OK.value())
                .build();
    }

    @PutMapping
    public BaseResponse updateUser(@RequestBody User user) {
        return BaseResponse.builder()
                .message("User successfully updated")
                .data(userService.editUser(user))
                .httpStatus(HttpStatus.OK.value())
                .build();
    }

    @GetMapping
    public BaseResponse getLoggedInUser(){
        return BaseResponse.builder()
                .data(userService.getLoggedUserDetails())
                .message("Logged user data fetched")
                .httpStatus(HttpStatus.OK.value())
                .build();

    }


    @GetMapping("/{userCode}")
    public BaseResponse getUserByUserCode(@PathVariable UUID userCode){
        return BaseResponse.builder()
                .message("User Successfully fetched")
                .data(userService.getUserByUserCode(userCode))
                .httpStatus(HttpStatus.OK.value())
                .build();
    }






}
