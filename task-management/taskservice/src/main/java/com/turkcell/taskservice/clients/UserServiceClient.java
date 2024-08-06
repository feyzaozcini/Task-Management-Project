package com.turkcell.taskservice.clients;



import com.turkcell.taskservice.core.configurations.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "userservice", url = "http://localhost:8093", configuration = FeignConfiguration.class) //Local için
public interface UserServiceClient {

    @GetMapping("/api/v1/auth/{id}")
    UserGetResponse getUserById(@PathVariable int id);

    @GetMapping("/api/v1/auth/ids")
    List<UserGetResponse> getUsersByIds(@RequestParam List<Integer> ids);
}