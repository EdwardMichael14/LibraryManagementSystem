package org.example.services;

import org.example.data.models.Book;
import org.example.data.models.Borrow;
import org.example.dtos.requests.BorrowBookRequest;
import org.example.dtos.requests.ReturnBookRequest;
import org.example.dtos.requests.UserLoginRequest;
import org.example.dtos.requests.UserSignUpRequest;
import org.example.dtos.responses.BorrowBookResponse;
import org.example.dtos.responses.ReturnBookResponse;
import org.example.dtos.responses.UserLoginResponse;
import org.example.dtos.responses.UserSignUpResponse;

import java.util.List;

public interface UserServices {

        UserSignUpResponse signUp(UserSignUpRequest request);
        UserLoginResponse login(UserLoginRequest request);
        List<Book> viewAllBooks();
        BorrowBookResponse borrowBook(BorrowBookRequest request);
        ReturnBookResponse returnBook(ReturnBookRequest request);
        List<Borrow> viewBorrowedBooks();

}
