package com.turkcell.projectservice.core.utils.details;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationExceptionDetails {
    private String title = "Validation Problem";
    private Map<String, String> errors;
}