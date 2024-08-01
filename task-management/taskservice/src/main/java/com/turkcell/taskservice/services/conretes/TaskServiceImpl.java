package com.turkcell.taskservice.services.conretes;

import com.turkcell.common.ProjectGetResponse;
import com.turkcell.common.UserGetResponse;
import com.turkcell.taskservice.clients.ProjectServiceClient;
import com.turkcell.taskservice.clients.UserServiceClient;
import com.turkcell.taskservice.entities.Task;
import com.turkcell.taskservice.repositories.TaskRepository;
import com.turkcell.taskservice.services.abstracts.TaskService;
import com.turkcell.taskservice.services.dtos.requests.TaskRequest;
import com.turkcell.taskservice.services.dtos.requests.TaskUpdateRequest;
import com.turkcell.taskservice.services.dtos.responses.TaskResponse;
import com.turkcell.taskservice.services.dtos.responses.TaskUpdateResponse;
import com.turkcell.taskservice.services.mappers.TaskMapper;
import com.turkcell.taskservice.services.rules.TaskBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
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

    private final TaskBusinessRules taskBusinessRules;

    @Override
    public TaskResponse addTask(TaskRequest request) {
        Task task= TaskMapper.INSTANCE.taskFromRequest(request);
        task.setStartDate(LocalDateTime.now());
        task.setEndDate(task.getDeadline().plusDays(10));
        task.setActive(true);
        task=taskRepository.save(task);

        //Alınan userları ve projeyi get isteği atarak getirme
        ProjectGetResponse project = projectServiceClient.getProjectById(task.getProjectId());
        List<UserGetResponse> users=userServiceClient.getUsersByIds(task.getUserIds());

        TaskResponse response= TaskMapper.INSTANCE.responseFromTask(task);

        response.setProject(project);
        response.setUsers(users);

        return response;
    }

    @Override
    public TaskUpdateResponse updateTask(TaskUpdateRequest request) {
        taskBusinessRules.isTaskExist(request.getId());
        Task task=taskRepository.findById(request.getId()).orElseThrow();
        TaskMapper.INSTANCE.updateTaskFromUpdateRequest(request,task);
        taskRepository.save(task);
        return TaskMapper.INSTANCE.updateResponseFromTask(task);
    }

    public TaskResponse buildResponse(Task task){
        TaskResponse response = TaskMapper.INSTANCE.responseFromTask(task);
        response.setProject(projectServiceClient.getProjectById(task.getProjectId()));
        response.setUsers(userServiceClient.getUsersByIds(task.getUserIds()));
        return response;
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        List<TaskResponse> responses = new ArrayList<>();
        for(Task task : taskRepository.findAll()){
            responses.add(buildResponse(task));
            taskRepository.save(task);
        }
        return responses;
    }

    @Override
    public TaskResponse getTaskById(Integer id) {
        taskBusinessRules.isTaskExist(id);
        return buildResponse(taskRepository.findById(id).orElseThrow());
    }


}
