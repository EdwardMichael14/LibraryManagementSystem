package dtos.responses;

import data.models.Book;
import lombok.Data;

@Data
public class AddBookResponse {
    private Book book;
    private String message;
}