package com.turkcell.taskservice.repositories;

import com.turkcell.taskservice.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;




public interface TaskRepository extends JpaRepository<Task,Integer> {
    boolean existsByProjectId(int projectId); //Proje var mı
    boolean existsById(int id);  //Task var mı

}
