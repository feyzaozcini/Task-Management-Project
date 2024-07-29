package com.turkcell.projectservice.repositories;

import com.turkcell.projectservice.entities.Project;
import com.turkcell.projectservice.services.dtos.requests.ProjectSearchRequest;
import com.turkcell.projectservice.services.dtos.responses.ProjectSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Integer>{
    boolean existsById(int id);

    boolean existsByIdAndActive(int id, boolean filter);

}
