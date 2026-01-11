package org.example.dtos.responses;

import org.example.data.models.Author;
import lombok.Data;
import org.example.data.models.Book;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BorrowBookResponse {

    private String message;
    private LocalDateTime borrowDate = LocalDateTime.now();
}
