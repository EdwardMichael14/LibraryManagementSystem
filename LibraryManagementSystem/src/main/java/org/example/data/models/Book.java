package org.example.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
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
