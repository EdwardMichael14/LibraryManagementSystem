package org.example.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class Borrow {
    @Id
    private String id;
    private User user;
    private Book book;
    private LocalDateTime borrowDate;


}
