package data.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Borrow {

    private User user;
    private Book book;
    private LocalDateTime borrowDate;


}
