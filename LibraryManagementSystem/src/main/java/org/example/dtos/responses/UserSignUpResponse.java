package org.example.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class UserSignUpResponse {

    private String message;
    public UserSignUpResponse(String message) {
        this.message = message;
    }

}
