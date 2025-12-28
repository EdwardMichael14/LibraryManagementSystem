package data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    @Id
    private String id;
    private String FullName;
    private String Email;
    private String Phone;
    private String Address;
    private String Username;
    private String Password;
    private List<Book> borrowedBooks = new ArrayList<>();
}
