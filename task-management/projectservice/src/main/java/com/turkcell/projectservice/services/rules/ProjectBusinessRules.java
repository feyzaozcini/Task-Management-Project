package com.turkcell.projectservice.services.rules;

import com.turkcell.projectservice.core.utils.types.NotFoundException;
import com.turkcell.projectservice.entities.Project;
import com.turkcell.projectservice.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectBusinessRules {
    private final ProjectRepository projectRepository;


    public void checkIfProjectExistsById(int projectId) {
        if (!projectRepository.existsById(projectId) || !projectRepository.findById(projectId).orElseThrow().getActive()) {
            throw new NotFoundException("Project Not Found!");
        }
    }

    public void checkIfAnyProjectIsExist(){
        if(projectRepository.findAll().stream().filter(Project::getActive).collect(Collectors.toList()).isEmpty())
            throw new NotFoundException("Any Project Not Found!");
    }
}
