package com.turkcell.userservice.controller;

import com.turkcell.userservice.services.abstracts.AuthService;
import com.turkcell.userservice.services.dtos.requests.LoginRequest;
import com.turkcell.userservice.services.dtos.requests.RegisterRequest;
import com.turkcell.userservice.services.dtos.responses.UserGetResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterRequest request){
        authService.register(request);
    }
    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest request){
        return authService.login(request);
    }

    @GetMapping("/{id}")
    public UserGetResponse getUserById(@PathVariable int id){
        return authService.getUserById(id);
    }




}