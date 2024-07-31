package com.turkcell.taskservice.controllers;

import com.turkcell.taskservice.services.abstracts.TaskService;
import com.turkcell.taskservice.services.dtos.requests.TaskRequest;
import com.turkcell.taskservice.services.dtos.responses.TaskGetResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {



    @Autowired
    private TaskService taskService;

    @PostMapping("add")
    public ResponseEntity<TaskGetResponse> addTask(@Valid @RequestBody TaskRequest request){
        TaskGetResponse taskResponse = taskService.addTask(request);
        return ResponseEntity.ok(taskResponse);
    }
}
