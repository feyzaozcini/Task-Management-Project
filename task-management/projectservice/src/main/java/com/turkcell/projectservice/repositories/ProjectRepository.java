package com.turkcell.projectservice.repositories;

import com.turkcell.projectservice.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Integer>{
    boolean existsById(int id);

    boolean existsByIdAndActive(int id, boolean filter);
}
