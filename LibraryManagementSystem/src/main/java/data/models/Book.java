package data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Book {
    @Id
    private String id;
    private String title;
    private Author author;
    private String isbn;
    private int quantity;
    private String edition;
    private BookStatus status;
    private int noOfCopies;


}
