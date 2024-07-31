package com.turkcell.taskservice.clients;


import com.turkcell.common.ProjectGetResponse;
import com.turkcell.taskservice.core.configurations.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "projectservice", url = "http://localhost:8087", configuration = FeignConfiguration.class) //Local için
public interface ProjectServiceClient {
    @GetMapping(value = "/api/v1/project/{id}")
    ProjectGetResponse getProjectById(@PathVariable int id);
}