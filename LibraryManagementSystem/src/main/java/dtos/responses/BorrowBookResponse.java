package dtos.responses;

import data.models.Author;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowBookResponse {
    private String bookTitle;
    private Author bookAuthor;
    private String edition;
    private String userId;
    private String message;
    private LocalDate borrowDate = LocalDate.now();
}
