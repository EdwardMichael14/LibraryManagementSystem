package dtos.requests;

import data.models.Author;
import data.models.Book;
import data.models.User;
import lombok.Data;

@Data
public class ReturnBookRequest {
    private Book book;
//    private String edition;
//    private String author;

}
