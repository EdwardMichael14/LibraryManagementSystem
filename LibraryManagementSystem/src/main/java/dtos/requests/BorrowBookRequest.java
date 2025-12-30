package dtos.requests;

import data.models.Book;
import data.models.User;
import lombok.Data;

@Data
public class BorrowBookRequest {

    private String title;
    private String author;
    private String edition;
}
