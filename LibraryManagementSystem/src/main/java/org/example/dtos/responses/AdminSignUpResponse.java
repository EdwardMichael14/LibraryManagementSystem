package org.example.dtos.responses;


import lombok.Data;

@Data

public class AdminSignUpResponse {
    private String message;
    public AdminSignUpResponse(String message) {
        this.message = message;
    }
}
