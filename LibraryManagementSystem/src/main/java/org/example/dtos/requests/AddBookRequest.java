package org.example.dtos.requests;

import org.example.data.models.Author;
import org.example.data.models.Book;
import lombok.Data;
import org.example.data.models.BookStatus;

@Data
public class AddBookRequest {
   private Book book;

}
