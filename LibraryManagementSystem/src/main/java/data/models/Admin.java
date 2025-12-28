package data.models;


import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Admin {
    @Id
    private String id;
    private String FullName;
    private String Email;
    private String Phone;
    private String Address;
    private String Username;
    private String Password;
}
