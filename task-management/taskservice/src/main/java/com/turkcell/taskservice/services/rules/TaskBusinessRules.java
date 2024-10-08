package com.turkcell.taskservice.services.rules;


import com.turkcell.taskservice.clients.ProjectServiceClient;
import com.turkcell.taskservice.clients.UserServiceClient;
import com.turkcell.taskservice.core.utils.types.NotFoundException;
import com.turkcell.taskservice.repositories.TaskRepository;
import com.turkcell.taskservice.clients.ProjectGetResponse;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TaskBusinessRules {
    private final TaskRepository taskRepository;
    private final ProjectServiceClient projectServiceClient;
    private final UserServiceClient userServiceClient;

    public void isProjectExist(int projectId){
        try{
            ProjectGetResponse response=projectServiceClient.getProjectById(projectId);
        }
        catch (FeignException.NotFound exception){
            throw new NotFoundException("Project Not Found");
        }
    }

    public void isTaskExist(int id){
        if(!taskRepository.existsById(id) || !taskRepository.findById(id).orElseThrow().isActive() )
            throw new NotFoundException("Task Not Found!");
    }

}
