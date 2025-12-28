package dtos.requests;

import lombok.Data;

@Data
public class UserLoginRequest {

    private String Email;
    private String Password;
}
