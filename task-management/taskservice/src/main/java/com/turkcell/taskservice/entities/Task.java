package com.turkcell.taskservice.entities;

import com.turkcell.taskservice.entities.Enum.TaskStatus;
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
@Table(name="tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "task_name")
    private String taskName;
    @Column(name = "task_description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status")
    private TaskStatus status;

    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "deadline")
    private LocalDateTime deadline;

    @ElementCollection
    @CollectionTable(name = "task_users", joinColumns = @JoinColumn(name = "task_id"))
    @Column(name = "user_id")
    private List<Integer> userIds;

    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "active")
    private boolean active;

}
