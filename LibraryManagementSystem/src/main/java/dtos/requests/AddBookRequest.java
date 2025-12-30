package dtos.requests;

import data.models.Book;
import lombok.Data;

@Data
public class AddBookRequest {
    private Book book;

}
