package com.turkcell.projectservice.controllers;

import com.turkcell.projectservice.services.abstracts.ProjectService;
import com.turkcell.projectservice.services.dtos.requests.ProjectAddRequest;
import com.turkcell.projectservice.services.dtos.requests.ProjectSearchRequest;
import com.turkcell.projectservice.services.dtos.requests.ProjectUpdateRequest;
import com.turkcell.projectservice.services.dtos.responses.CreatedProjectResponse;
import com.turkcell.projectservice.services.dtos.responses.ProjectGetResponse;
import com.turkcell.projectservice.services.dtos.responses.ProjectSearchResponse;
import com.turkcell.projectservice.services.dtos.responses.ProjectUpdateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/add")
    public CreatedProjectResponse addProject(@Valid @RequestBody ProjectAddRequest request){
        return projectService.addProject(request);
    }

    @GetMapping("/all")
    public List<ProjectGetResponse> getAllProjects(){
        return projectService.getAllProject();
    }

    @GetMapping("/{id}")
    public ProjectGetResponse getProjectById(@PathVariable int id){
        return projectService.getProjectById(id);
    }

    @PutMapping("/update")
    public ProjectUpdateResponse updateProduct(@Valid @RequestBody ProjectUpdateRequest request){
        return projectService.updateProject(request);
    }

    @PostMapping("/search")
    public ResponseEntity<List<ProjectSearchResponse>> searchProducts(@RequestBody ProjectSearchRequest request) {
        List<ProjectSearchResponse> results = projectService.searchProject(request);
        return ResponseEntity.ok(results);
    }

    @DeleteMapping("delete/{id}")
    public void deleteProjectById(@PathVariable int id){
        projectService.deleteProjectById(id);
    }

}
