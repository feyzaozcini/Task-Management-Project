package com.turkcell.userservice.core.utils.details;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessProblemDetails{
    private  String title="Business Problem";
    private String detail;
}