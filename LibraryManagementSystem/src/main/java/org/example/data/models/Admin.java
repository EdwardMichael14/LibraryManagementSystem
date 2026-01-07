package org.example.data.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "admins")
public class Admin {
    @Id
    private String id;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String password;
    private Role role =  Role.ADMIN;
}
