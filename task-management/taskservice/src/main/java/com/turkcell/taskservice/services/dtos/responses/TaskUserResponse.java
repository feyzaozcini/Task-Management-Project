package com.turkcell.taskservice.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskUserResponse {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
}
