package com.turkcell.projectservice.services.dtos.requests;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectAddRequest {

    @NotBlank
    private String projectName;
    @NotBlank
    private String description;
    @NotBlank
    private String owner;

}
