package org.example.dtos.responses;

import org.example.data.models.Book;
import lombok.Data;

@Data
public class ReturnBookResponse {
    private Book book;
    private String message;
}
