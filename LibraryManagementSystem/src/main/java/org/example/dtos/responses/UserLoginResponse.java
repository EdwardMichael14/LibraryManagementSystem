package org.example.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserLoginResponse {

    private String message;
    public UserLoginResponse(String message) {
        this.message = message;
    }
}
