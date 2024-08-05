package com.turkcell.taskservice.services.dtos.responses;


import com.turkcell.taskservice.entities.Enum.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdateResponse {

    private Integer id;
    private String taskName;
    private String description;
    private TaskStatus status;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime deadline;


}