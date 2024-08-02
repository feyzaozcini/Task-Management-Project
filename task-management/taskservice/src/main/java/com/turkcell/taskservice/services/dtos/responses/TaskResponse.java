package com.turkcell.taskservice.services.dtos.responses;

import com.turkcell.common.ProjectGetResponse;
import com.turkcell.common.UserGetResponse;
import com.turkcell.taskservice.entities.Enum.TaskStatus;
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
public class TaskResponse {

    private Integer id;
    private String taskName;
    private String description;
    private TaskStatus status;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime deadline;

    private ProjectGetResponse project;
    private List<UserGetResponse> users;

    private String errorMessage;
    public TaskResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
