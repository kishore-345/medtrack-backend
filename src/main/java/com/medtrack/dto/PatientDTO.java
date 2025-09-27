package com.medtrack.dto;

import jakarta.validation.constraints.NotBlank;

public class PatientDTO {

    @NotBlank(message = "Name is required")
    private String name;

    // Getter
    public String getName() {
        return name;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }
}