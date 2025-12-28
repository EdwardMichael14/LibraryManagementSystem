package services;

import data.models.Book;
import dtos.requests.BorrowBookRequest;
import dtos.requests.ReturnBookRequest;
import dtos.requests.UserLoginRequest;
import dtos.requests.UserSignUpRequest;
import dtos.responses.BorrowBookResponse;
import dtos.responses.ReturnBookResponse;
import dtos.responses.UserLoginResponse;
import dtos.responses.UserSignUpResponse;

import java.util.List;

public interface UserServices {

        UserSignUpResponse signUp(UserSignUpRequest request);
        UserLoginResponse login(UserLoginRequest request);
        List<Book> viewBooks();
        BorrowBookResponse borrowBook(BorrowBookRequest request);
        ReturnBookResponse returnBook(ReturnBookRequest request);

}
