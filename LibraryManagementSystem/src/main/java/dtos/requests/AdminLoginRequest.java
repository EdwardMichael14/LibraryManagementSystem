package dtos.requests;

import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
public class AdminLoginRequest {
    @Id
    private String Email;
    private String Password;
}
