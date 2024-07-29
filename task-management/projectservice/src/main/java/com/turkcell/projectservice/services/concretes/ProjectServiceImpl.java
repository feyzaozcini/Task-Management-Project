package com.turkcell.projectservice.services.concretes;

import com.turkcell.projectservice.entities.Project;
import com.turkcell.projectservice.repositories.ProjectRepository;
import com.turkcell.projectservice.services.abstracts.ProjectService;
import com.turkcell.projectservice.services.dtos.requests.ProjectAddRequest;
import com.turkcell.projectservice.services.dtos.requests.ProjectUpdateRequest;
import com.turkcell.projectservice.services.dtos.responses.CreatedProjectResponse;
import com.turkcell.projectservice.services.dtos.responses.ProjectGetResponse;
import com.turkcell.projectservice.services.dtos.responses.ProjectUpdateResponse;
import com.turkcell.projectservice.services.mappers.ProjectMapper;
import com.turkcell.projectservice.services.rules.ProjectBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectBusinessRules projectBusinessRules;


    @Override
    public CreatedProjectResponse addProject(ProjectAddRequest request) {
        Project project= ProjectMapper.INSTANCE.projectFromAddRequest(request);
        project.setCreatedDate(LocalDateTime.now());
        project.setActive(true);
        projectRepository.save(project);
        return ProjectMapper.INSTANCE.getResponseFromCreatedProject(project);
    }

    @Override
    public List<ProjectGetResponse> getAllProject() {
        projectBusinessRules.checkIfAnyProjectIsExist();
        return projectRepository.findAll().stream().filter(Project::getActive).map((project)-> ProjectMapper.INSTANCE.getResponseFromProject(project)).collect(Collectors.toList());
    }

    @Override
    public ProjectGetResponse getProjectById(int id) {
        projectBusinessRules.checkIfProjectExistsById(id);
        return ProjectMapper.INSTANCE.getResponseFromProject(projectRepository.findById(id).orElseThrow());

    }

    @Override
    public ProjectUpdateResponse updateProject(ProjectUpdateRequest request) {
        projectBusinessRules.checkIfProjectExistsById(request.getId());
        Project project=projectRepository.findById(request.getId()).orElseThrow();
        project.setUpdatedDate(LocalDateTime.now());
        ProjectMapper.INSTANCE.projectFromUpdateRequest(request,project);
        projectRepository.save(project);
        return ProjectMapper.INSTANCE.getResponseFromUpdatedProduct(project);
    }

    @Override
    public void deleteProjectById(int id) {
        projectBusinessRules.checkIfProjectExistsById(id);
        Project project=projectRepository.findById(id).orElseThrow();
        project.setActive(false);
        project.setDeletedDate(LocalDateTime.now());
        projectRepository.save(project);
    }
}
