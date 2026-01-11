package org.example.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data

public class Borrow {
    @Id
    private String id;
    private String email;
    private String bookId;
    private LocalDateTime borrowDate;


}
