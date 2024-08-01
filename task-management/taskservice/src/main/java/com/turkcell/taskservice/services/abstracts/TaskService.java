package com.turkcell.taskservice.services.abstracts;


import com.turkcell.taskservice.services.dtos.requests.TaskRequest;
import com.turkcell.taskservice.services.dtos.requests.TaskSearchRequest;
import com.turkcell.taskservice.services.dtos.requests.TaskUpdateRequest;
import com.turkcell.taskservice.services.dtos.responses.TaskResponse;
import com.turkcell.taskservice.services.dtos.responses.TaskSearchResponse;
import com.turkcell.taskservice.services.dtos.responses.TaskUpdateResponse;

import java.util.List;


public interface TaskService {
    TaskResponse addTask(TaskRequest request);

    TaskUpdateResponse updateTask(TaskUpdateRequest request);

    List<TaskResponse> getAllTasks();

    TaskResponse getTaskById(Integer id);

    List<TaskSearchResponse> searchTask(TaskSearchRequest request);
}
