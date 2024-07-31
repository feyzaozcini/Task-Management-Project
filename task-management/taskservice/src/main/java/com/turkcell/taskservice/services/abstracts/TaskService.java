package com.turkcell.taskservice.services.abstracts;

import com.turkcell.taskservice.services.dtos.requests.TaskRequest;
import com.turkcell.taskservice.services.dtos.responses.TaskGetResponse;
import org.springframework.stereotype.Service;


public interface TaskService {
    TaskGetResponse addTask(TaskRequest request);
}
