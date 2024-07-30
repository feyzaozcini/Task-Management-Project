package com.turkcell.userservice.services.abstracts;


import com.turkcell.userservice.services.dtos.requests.LoginRequest;
import com.turkcell.userservice.services.dtos.requests.RegisterRequest;
import com.turkcell.userservice.services.dtos.responses.UserGetResponse;

public interface AuthService {
    void register(RegisterRequest registerRequest);

    String login(LoginRequest loginRequest);

    UserGetResponse getUserById(int id);
}
