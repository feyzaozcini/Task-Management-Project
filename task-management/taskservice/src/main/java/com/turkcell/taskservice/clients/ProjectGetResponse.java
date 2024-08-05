package com.turkcell.taskservice.clients;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectGetResponse {
    private Integer id;

    private String projectName;

    private String owner;

    private String description;

    private Boolean active;
}