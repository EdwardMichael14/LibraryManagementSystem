package org.example.services;

import org.example.data.models.Book;
import org.example.data.models.Borrow;
import org.example.data.models.User;
import org.example.dtos.requests.AddBookRequest;
import org.example.dtos.requests.AdminLoginRequest;
import org.example.dtos.requests.AdminSignUpRequest;
import org.example.dtos.requests.UserSignUpRequest;
import org.example.dtos.responses.*;

import java.util.List;

public interface AdminServices {

    AdminLoginResponse adminLogin(AdminLoginRequest request);
    AdminSignUpResponse adminSignUp(AdminSignUpRequest request);
    List<UserResponse> viewUsers();
    List<Book> viewAllBooks();
    List<Borrow> viewBorrowedBooks();
    UserSignUpResponse registerUser(UserSignUpRequest request);
    AddBookResponse addBook(AddBookRequest request);
}
