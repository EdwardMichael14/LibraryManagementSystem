package org.example.utils;

import org.example.data.models.Book;
import org.example.data.models.Borrow;
import org.example.data.models.User;
import org.example.dtos.requests.AddBookRequest;
import org.example.dtos.requests.UserSignUpRequest;
import org.example.dtos.responses.BorrowBookResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Mapper {
    public static User mapSignUpUser(UserSignUpRequest request) {

            User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setUserName(request.getUserName());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());

        return user;
    }

    public static BorrowBookResponse mapBorrowBookResponse(Borrow borrow) {

        BorrowBookResponse borrowBookResponse = new BorrowBookResponse();

        borrowBookResponse.setUserId(borrow.getUser().getId());
        borrowBookResponse.setBookAuthor(borrow.getBook().getAuthor());
        borrowBookResponse.setBookTitle(borrow.getBook().getTitle());
        borrowBookResponse.setEdition(borrow.getBook().getEdition());
        borrowBookResponse.setMessage("Book borrowed");
        borrowBookResponse.setBorrowDate(borrow.getBorrowDate());


        return borrowBookResponse;
    }

    public static Book mapAddBook(AddBookRequest request) {

        Book book = new Book();

        book.setAuthor(request.getBook().getAuthor());
        book.setTitle(request.getBook().getTitle());
        book.setEdition(request.getBook().getEdition());
        book.setNoOfCopies(request.getBook().getNoOfCopies());
        book.setIsbn(request.getBook().getIsbn());
        book.setStatus(request.getBook().getStatus());

        return book;
    }




}
