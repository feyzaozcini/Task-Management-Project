package com.turkcell.taskservice.services.abstracts;

import com.turkcell.taskservice.entities.Task;
import com.turkcell.taskservice.services.dtos.requests.TaskRequest;
import com.turkcell.taskservice.services.dtos.requests.TaskUpdateRequest;
import com.turkcell.taskservice.services.dtos.responses.TaskResponse;
import com.turkcell.taskservice.services.dtos.responses.TaskUpdateResponse;

import java.util.List;


public interface TaskService {
    TaskResponse addTask(TaskRequest request);

    TaskUpdateResponse updateTask(TaskUpdateRequest request);

    List<TaskResponse> getAllTasks();

    TaskResponse getTaskById(Integer id);
}
