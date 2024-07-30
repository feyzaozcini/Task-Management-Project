package com.turkcell.taskservice.clients.dtos.userservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserGetResponse {
    private int id;

    private String email;

    private String firstName;

    private String lastName;
}
