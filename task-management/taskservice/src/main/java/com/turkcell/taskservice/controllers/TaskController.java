package com.turkcell.taskservice.controllers;

import com.turkcell.taskservice.services.abstracts.TaskService;
import com.turkcell.taskservice.services.dtos.requests.TaskRequest;
import com.turkcell.taskservice.services.dtos.requests.TaskUpdateRequest;
import com.turkcell.taskservice.services.dtos.responses.TaskResponse;
import com.turkcell.taskservice.services.dtos.responses.TaskUpdateResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/all")
    public List<TaskResponse> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Integer id){
        return taskService.getTaskById(id);
    }


    @PostMapping("add")
    public ResponseEntity<TaskResponse> addTask(@Valid @RequestBody TaskRequest request){
        TaskResponse taskResponse = taskService.addTask(request);
        return ResponseEntity.ok(taskResponse);
    }

    @PutMapping("/update")
    public TaskUpdateResponse updateTask(@RequestBody TaskUpdateRequest request){
        return taskService.updateTask(request);
    }


}
