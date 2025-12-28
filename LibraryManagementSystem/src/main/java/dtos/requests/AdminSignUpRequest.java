package dtos.requests;

import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
public class AdminSignUpRequest {

    private String fullName;
    @Id
    private String email;
    private String phone;
    private String userName;
    private String password;
}
