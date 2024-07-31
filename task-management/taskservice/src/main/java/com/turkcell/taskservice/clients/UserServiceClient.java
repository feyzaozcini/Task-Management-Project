package com.turkcell.taskservice.clients;


import com.turkcell.common.UserGetResponse;
import com.turkcell.taskservice.core.configurations.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userservice", url = "http://localhost:8091", configuration = FeignConfiguration.class) //Local için
public interface UserServiceClient {
    @GetMapping(value = "/api/v1/auth/{id}")
    UserGetResponse getUserById(@PathVariable int id);
}