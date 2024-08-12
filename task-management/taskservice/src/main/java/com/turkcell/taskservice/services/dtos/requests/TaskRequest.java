package com.turkcell.taskservice.services.dtos.requests;

import com.turkcell.taskservice.entities.enums.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    @NotNull
    private String taskName;
    @NotNull
    private String description;
    @NotNull
    private TaskStatus status;
    @NotNull
    private LocalDateTime deadline;
    @NotNull
    private int projectId;
    @NotNull
    private List<Integer> userIds;
}
