package com.turkcell.projectservice.services.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProjectSearchRequest {
    private Integer id;

    private String projectName;

    private String owner;
}
