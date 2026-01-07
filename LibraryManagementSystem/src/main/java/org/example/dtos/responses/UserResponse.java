package org.example.dtos.responses;

public class UserResponse {
    private String id;
    private String fullName;
    private String email;

    public UserResponse(String id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
}

