package dtos.responses;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowBookResponse {
    private String bookTitle;
    private String bookAuthor;
    private String edition;
    private LocalDate borrowDate = LocalDate.now();
}
