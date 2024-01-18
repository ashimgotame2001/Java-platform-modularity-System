package com.swifttech.service;

import com.swifttech.entity.User;
import com.swifttech.response.AuthenticationRequest;
import com.swifttech.response.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse authenticate (AuthenticationRequest authenticationRequest);

    User addUser(User user);

}
