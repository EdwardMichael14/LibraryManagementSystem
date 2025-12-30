package services;

import data.models.Admin;
import data.models.Book;
import data.models.Borrow;
import data.models.User;
import data.repositories.AdminRepository;
import data.repositories.BookRepository;
import data.repositories.BorrowRepository;
import data.repositories.UserRepository;
import dtos.requests.AddBookRequest;
import dtos.requests.AdminLoginRequest;
import dtos.requests.AdminSignUpRequest;
import dtos.requests.UserSignUpRequest;
import dtos.responses.*;
import exceptions.IncorrectLogin;
import exceptions.UserAlreadyExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static utils.Mapper.mapAddBook;

@Service
public class AdminServicesImpl implements AdminServices {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BorrowRepository borrowRepository;

    @Override
    public AdminLoginResponse AdminLogin(AdminLoginRequest request) {
        Admin admin = (adminRepository.findByEmail(request.getEmail()));

        if (admin == null) {
            throw new IncorrectLogin("incorrect email or password");
        }

        if (!admin.getPassword().equals(request.getPassword())) {
            throw new IncorrectLogin("incorrect password");
        }
        return new AdminLoginResponse("Login Successfull");
    }



    @Override
    public AdminSignUpResponse AdminSignUp(AdminSignUpRequest request) {

            if(adminRepository.existsById(request.getEmail())) {
                throw new UserAlreadyExist("Email already exist");
            }

            Admin admin = new Admin();

            admin.setEmail(request.getEmail());
            admin.setPassword(request.getPassword());
            admin.setFullName(request.getFullName());
            admin.setPassword(request.getPassword());
            admin.setPhone(request.getPhone());
            adminRepository.save(admin);

            return new AdminSignUpResponse("Registered successfully");
    }

    @Override
    public List<User> viewUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Book> viewAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Borrow> viewBorrowedBooks() {
        return borrowRepository.findAll();
    }

    @Override
    public UserSignUpResponse registerUser(UserSignUpRequest request) {
        UserServicesImpl userServices = new UserServicesImpl();
        return userServices.signUp(request);
    }

    @Override
    public AddBookResponse addBook(AddBookRequest request) {
        Book book = mapAddBook(request);

        bookRepository.save(book);

        AddBookResponse addBookResponse = new AddBookResponse();
        addBookResponse.setBook(book);
        addBookResponse.setMessage("Book added successfully");
        return addBookResponse;
    }
}