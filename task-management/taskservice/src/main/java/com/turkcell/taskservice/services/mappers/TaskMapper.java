package com.turkcell.taskservice.services.mappers;

import com.turkcell.common.UserGetResponse;
import com.turkcell.taskservice.entities.Task;
import com.turkcell.taskservice.services.dtos.requests.TaskRequest;
import com.turkcell.taskservice.services.dtos.requests.TaskUpdateRequest;
import com.turkcell.taskservice.services.dtos.responses.TaskResponse;
import com.turkcell.taskservice.services.dtos.responses.TaskUpdateResponse;
import com.turkcell.taskservice.services.dtos.responses.TaskUserResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper{

    TaskMapper INSTANCE= Mappers.getMapper(TaskMapper.class);

    Task taskFromRequest(TaskRequest request);

    @Mapping(target = "project", ignore = true)
    @Mapping(target = "users", ignore = true)
    TaskResponse responseFromTask(Task task);

    @Mapping(target = "id", ignore = true)
    TaskUserResponse userResponseFromUser(UserGetResponse userGetResponse);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTaskFromUpdateRequest(TaskUpdateRequest request, @MappingTarget Task task);

    TaskUpdateResponse updateResponseFromTask(Task task);



}
