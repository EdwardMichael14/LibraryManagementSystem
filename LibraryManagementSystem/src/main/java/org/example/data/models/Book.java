package org.example.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Books")
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private String isbn;
    private String edition;
    private BookStatus status = BookStatus.AVAILABLE;
    private int noOfCopies;


}
