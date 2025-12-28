package services;

import data.models.Admin;
import data.models.Book;
import data.models.Borrow;
import data.models.User;
import data.repositories.AdminRepository;
import data.repositories.BookRepository;
import data.repositories.BorrowRepository;
import data.repositories.UserRepository;
import dtos.requests.AdminLoginRequest;
import dtos.requests.AdminSignUpRequest;
import dtos.requests.UserSignUpRequest;
import dtos.responses.AdminLoginResponse;
import dtos.responses.AdminSignUpResponse;
import dtos.responses.UserSignUpResponse;
import exceptions.UserAlreadyExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
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
    public List<Book> viewBooks() {
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
}