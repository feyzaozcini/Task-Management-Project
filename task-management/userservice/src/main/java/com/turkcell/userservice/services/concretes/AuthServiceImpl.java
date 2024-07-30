package com.turkcell.userservice.services.concretes;


import com.turkcell.tcellfinalcore.services.BaseJwtService;
import com.turkcell.userservice.core.utils.types.BusinessException;
import com.turkcell.userservice.entities.User;
import com.turkcell.userservice.repositories.UserRepository;
import com.turkcell.userservice.services.abstracts.AuthService;
import com.turkcell.userservice.services.abstracts.UserService;
import com.turkcell.userservice.services.dtos.requests.LoginRequest;
import com.turkcell.userservice.services.dtos.requests.RegisterRequest;
import com.turkcell.userservice.services.dtos.responses.UserGetResponse;
import com.turkcell.userservice.services.mappers.UserMapper;
import com.turkcell.userservice.services.rules.UserBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final BaseJwtService jwtService;
    private final UserBusinessRules userBusinessRules;
    private final UserRepository userRepository;

    @Override
    public void register(RegisterRequest request) {
        userBusinessRules.checkIfUserAlreadyExists(request.getEmail());
        User user = UserMapper.INSTANCE.userFromRegisterRequest(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedDate(LocalDateTime.now());
        userService.add(user);
    }

    @Override
    public String login(LoginRequest request) {
        userBusinessRules.checkIfUserNotExists(request.getEmail());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (AuthenticationException exception) {
            throw new BusinessException("Username or Password wrong!");
        }

        return jwtService.generateTokenWithClaims(userService.loadUserByUsername(request.getEmail()));
    }

    @Override
    public UserGetResponse getUserById(int id) {
        userBusinessRules.checkIfUserExistsById(id);
        return UserMapper.INSTANCE.getResponseFromUser(userRepository.findById(id).orElseThrow());
    }
}
