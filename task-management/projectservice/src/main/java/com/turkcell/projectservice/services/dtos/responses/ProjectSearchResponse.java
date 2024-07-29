package com.turkcell.projectservice.services.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectSearchResponse {
    private Integer id;

    private String projectName;

    private String owner;

    private String description;

    private Boolean active;
}
