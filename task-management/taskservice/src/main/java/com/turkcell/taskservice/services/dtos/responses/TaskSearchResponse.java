package com.turkcell.taskservice.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskSearchResponse {

    private Integer id;
    private String taskName;
    private Integer projectId;

}
