package com.turkcell.taskservice.services.mappers;

import com.turkcell.common.ProjectGetResponse;
import com.turkcell.common.UserGetResponse;
import com.turkcell.taskservice.entities.Task;
import com.turkcell.taskservice.services.dtos.requests.TaskRequest;
import com.turkcell.taskservice.services.dtos.responses.TaskGetResponse;
import com.turkcell.taskservice.services.dtos.responses.TaskUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TaskMapper{

    TaskMapper INSTANCE= Mappers.getMapper(TaskMapper.class);

    Task taskFromRequest(TaskRequest request);

    @Mapping(target = "project", ignore = true)
    @Mapping(target = "users", ignore = true)
    TaskGetResponse responseFromTask(Task task);

    @Mapping(target = "id", ignore = true)
    TaskUserResponse userResponseFromUser(UserGetResponse userGetResponse);

}
