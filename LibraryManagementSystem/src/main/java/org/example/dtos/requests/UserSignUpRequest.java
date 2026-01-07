package org.example.dtos.requests;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserSignUpRequest {

    private String fullName;
    private String email;
    private String phone;
    private String userName;
    private String password;
}
