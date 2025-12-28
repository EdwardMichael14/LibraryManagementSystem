package dtos.requests;

import data.models.Author;
import data.models.Book;
import data.models.User;
import lombok.Data;

@Data
public class ReturnBookRequest {
    private User user;
    private String title;
    private String edition;
    private String author;

}
