package com.turkcell.taskservice.services.conretes;

import com.turkcell.common.ProjectGetResponse;
import com.turkcell.common.UserGetResponse;
import com.turkcell.taskservice.clients.ProjectServiceClient;
import com.turkcell.taskservice.clients.UserServiceClient;
import com.turkcell.taskservice.entities.Task;
import com.turkcell.taskservice.repositories.TaskRepository;
import com.turkcell.taskservice.services.abstracts.TaskService;
import com.turkcell.taskservice.services.dtos.requests.TaskRequest;
import com.turkcell.taskservice.services.dtos.responses.TaskGetResponse;
import com.turkcell.taskservice.services.mappers.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    @Autowired
    private final TaskRepository taskRepository;

    @Autowired
    private final UserServiceClient userServiceClient;

    @Autowired
    private final ProjectServiceClient projectServiceClient;


    @Override
    public TaskGetResponse addTask(TaskRequest request) {
        Task task= TaskMapper.INSTANCE.taskFromRequest(request);
        task.setStartDate(LocalDateTime.now());
        task.setEndDate(task.getDeadline().plusDays(10));
        task=taskRepository.save(task);

        //Alınan userları ve projeyi get isteği atarak getirme
        ProjectGetResponse project = projectServiceClient.getProjectById(task.getProjectId());
        List<UserGetResponse> users=userServiceClient.getUsersByIds(task.getUserIds());

        TaskGetResponse response= TaskMapper.INSTANCE.responseFromTask(task);

        response.setProject(project);
        response.setUsers(users);

        return response;
    }
}
