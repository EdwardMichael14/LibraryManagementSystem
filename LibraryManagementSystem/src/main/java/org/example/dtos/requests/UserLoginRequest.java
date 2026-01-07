package org.example.dtos.requests;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserLoginRequest {
    private String email;
    private String password;
}
