package com.turkcell.projectservice.entities;


import com.turkcell.projectservice.entities.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;


    @Column(name = "project_name")
    private String projectName;
    @Column(name = "project_description")
    private String description;
    @Column(name = "owner")
    private String owner;
    @Column(name = "status")
    private ProjectStatus status;

    @ElementCollection
    @CollectionTable(name = "user_ids")
    @Column(name = "users")
    private List<Integer> userIds;
}
