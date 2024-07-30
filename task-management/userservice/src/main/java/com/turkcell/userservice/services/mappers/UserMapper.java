package com.turkcell.userservice.services.mappers;

import com.turkcell.userservice.entities.User;
import com.turkcell.userservice.services.dtos.requests.LoginRequest;
import com.turkcell.userservice.services.dtos.requests.RegisterRequest;
import com.turkcell.userservice.services.dtos.responses.UserGetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    User userFromRegisterRequest(RegisterRequest request);

    User userFromLoginRequest(LoginRequest request);

    UserGetResponse getResponseFromUser(User user);
}