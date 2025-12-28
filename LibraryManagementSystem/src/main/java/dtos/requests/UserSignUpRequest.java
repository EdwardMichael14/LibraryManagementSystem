package dtos.requests;

import lombok.Data;

@Data
public class UserSignUpRequest {

    private String fullName;
    private String email;
    private String phone;
    private String userName;
    private String password;
}
