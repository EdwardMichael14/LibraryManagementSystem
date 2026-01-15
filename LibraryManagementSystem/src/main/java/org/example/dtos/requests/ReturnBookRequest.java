package org.example.dtos.requests;

import org.example.data.models.Book;
import lombok.Data;

@Data
public class ReturnBookRequest {
    private Book book;

}
