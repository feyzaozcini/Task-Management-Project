package com.turkcell.projectservice.services.abstracts;


import com.turkcell.projectservice.services.dtos.requests.ProjectAddRequest;
import com.turkcell.projectservice.services.dtos.requests.ProjectUpdateRequest;
import com.turkcell.projectservice.services.dtos.responses.CreatedProjectResponse;
import com.turkcell.projectservice.services.dtos.responses.ProjectGetResponse;
import com.turkcell.projectservice.services.dtos.responses.ProjectUpdateResponse;

import java.util.List;

public interface ProjectService {
   CreatedProjectResponse addProject(ProjectAddRequest request);
   List<ProjectGetResponse> getAllProject();
   ProjectGetResponse getProjectById(int id);
   ProjectUpdateResponse updateProject(ProjectUpdateRequest request);
   void deleteProjectById(int id);
}
