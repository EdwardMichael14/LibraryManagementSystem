package services;

import data.models.Book;
import data.models.Borrow;
import data.models.User;
import dtos.requests.AdminLoginRequest;
import dtos.requests.AdminSignUpRequest;
import dtos.requests.UserSignUpRequest;
import dtos.responses.AdminLoginResponse;
import dtos.responses.AdminSignUpResponse;
import dtos.responses.UserSignUpResponse;

import java.util.List;

public interface AdminServices {

    AdminLoginResponse AdminLogin(AdminLoginRequest request);
    AdminSignUpResponse AdminSignUp(AdminSignUpRequest request);
    List<User> viewUsers();
    List<Book> viewBooks();
    List<Borrow> viewBorrowedBooks();
    UserSignUpResponse registerUser(UserSignUpRequest request);

}
