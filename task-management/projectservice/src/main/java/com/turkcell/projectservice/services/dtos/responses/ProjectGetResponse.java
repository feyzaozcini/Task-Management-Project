package com.turkcell.projectservice.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectGetResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String projectName;

    private String description;

    private String owner;
}
