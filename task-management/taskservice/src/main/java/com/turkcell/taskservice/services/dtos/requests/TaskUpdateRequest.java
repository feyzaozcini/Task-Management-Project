package com.turkcell.taskservice.services.dtos.requests;



import com.turkcell.taskservice.entities.enums.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdateRequest {
    @NotNull
    private Integer id;

    @NotNull
    private TaskStatus status;

}