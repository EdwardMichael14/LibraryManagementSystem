package org.example.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class AdminLoginResponse {

    private String message;
    public AdminLoginResponse(String message) {
        this.message = message;
    }
}
