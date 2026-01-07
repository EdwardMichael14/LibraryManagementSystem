package org.example.dtos.requests;

import lombok.Data;

@Data
public class BorrowBookRequest {

    private String title;
    private String author;
    private String edition;
}
