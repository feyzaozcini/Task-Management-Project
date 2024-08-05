package com.turkcell.taskservice.controllers;

import com.turkcell.taskservice.services.abstracts.TaskService;
import com.turkcell.taskservice.services.dtos.requests.TaskRequest;
import com.turkcell.taskservice.services.dtos.requests.TaskSearchRequest;
import com.turkcell.taskservice.services.dtos.requests.TaskUpdateRequest;
import com.turkcell.taskservice.services.dtos.responses.TaskResponse;
import com.turkcell.taskservice.services.dtos.responses.TaskSearchResponse;
import com.turkcell.taskservice.services.dtos.responses.TaskUpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<TaskResponse> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Integer id){
        return taskService.getTaskById(id);
    }


    @PostMapping
    public ResponseEntity<TaskResponse> addTask(@RequestBody TaskRequest request){
            TaskResponse taskResponse = taskService.addTask(request);
            return ResponseEntity.ok(taskResponse);
    }

    @PostMapping("/search")
    public ResponseEntity<List<TaskSearchResponse>> searchProducts(@RequestBody TaskSearchRequest request) {
        List<TaskSearchResponse> results = taskService.searchTask(request);
        return ResponseEntity.ok(results);
    }

    @PutMapping
    public TaskUpdateResponse updateTask(@RequestBody TaskUpdateRequest request){
        return taskService.updateTask(request);
    }


}
