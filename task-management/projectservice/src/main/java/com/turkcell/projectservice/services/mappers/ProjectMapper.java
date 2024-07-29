package com.turkcell.projectservice.services.mappers;

import com.turkcell.projectservice.entities.Project;
import com.turkcell.projectservice.services.dtos.requests.ProjectAddRequest;
import com.turkcell.projectservice.services.dtos.requests.ProjectUpdateRequest;
import com.turkcell.projectservice.services.dtos.responses.CreatedProjectResponse;
import com.turkcell.projectservice.services.dtos.responses.ProjectGetResponse;
import com.turkcell.projectservice.services.dtos.responses.ProjectUpdateResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);


    @Mapping(target = "id", ignore = true)
    Project projectFromAddRequest(ProjectAddRequest request);

    CreatedProjectResponse getResponseFromCreatedProject(Project project);

    ProjectGetResponse getResponseFromProject(Project project);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void projectFromUpdateRequest(ProjectUpdateRequest request, @MappingTarget Project project);

    ProjectUpdateResponse getResponseFromUpdatedProduct(Project project);
}
