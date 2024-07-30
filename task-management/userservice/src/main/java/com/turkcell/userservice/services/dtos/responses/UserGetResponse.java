package com.turkcell.userservice.services.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserGetResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
}
