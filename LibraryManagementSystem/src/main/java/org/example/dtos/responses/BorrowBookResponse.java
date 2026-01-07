package org.example.dtos.responses;

import org.example.data.models.Author;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BorrowBookResponse {
    private String bookTitle;
    private String bookAuthor;
    private String edition;
    private String userId;
    private String message;
    private LocalDateTime borrowDate = LocalDateTime.now();
}
