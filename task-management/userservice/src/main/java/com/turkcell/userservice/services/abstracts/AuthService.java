package com.turkcell.userservice.services.abstracts;

import com.turkcell.userservice.services.dtos.requests.LoginRequest;
import com.turkcell.userservice.services.dtos.requests.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest registerRequest);

    String login(LoginRequest loginRequest);
}
