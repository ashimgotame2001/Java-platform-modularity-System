package com.swifttech.controller;

import com.swifttech.entity.User;
import com.swifttech.response.AuthenticationRequest;
import com.swifttech.response.AuthenticationResponse;
import com.swifttech.response.BaseResponse;
import com.swifttech.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest){
       return authenticationService.authenticate(authenticationRequest);
    }

    @PostMapping("register")
    public BaseResponse addUsers(@RequestBody User user) {
        return BaseResponse.builder().message("user created").httpStatus(201).data(authenticationService.addUser(user)).build();
    }
}
