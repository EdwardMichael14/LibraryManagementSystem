package org.example.services;

import org.example.data.models.Admin;
import org.example.data.models.Book;
import org.example.data.models.Borrow;
import org.example.data.repositories.AdminRepository;
import org.example.data.repositories.BookRepository;
import org.example.data.repositories.BorrowRepository;
import org.example.data.repositories.UserRepository;
import org.example.dtos.requests.AddBookRequest;
import org.example.dtos.requests.AdminLoginRequest;
import org.example.dtos.requests.AdminSignUpRequest;
import org.example.dtos.requests.UserSignUpRequest;
import org.example.dtos.responses.*;
import org.example.exceptions.IncorrectLogin;
import org.example.exceptions.UserAlreadyExist;
import org.example.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.utils.Mapper.mapAddBook;

@Service
public class AdminServicesImpl implements AdminServices {
    @Autowired
   private AdminRepository adminRepository;
    @Autowired
   private UserRepository userRepository;
    @Autowired
   private BookRepository bookRepository;
    @Autowired
   private BorrowRepository borrowRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserServices userServices;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;

    @Override
    public AdminSignUpResponse adminSignUp(AdminSignUpRequest request) {

           if(adminRepository.findByEmail(request.getEmail()).isPresent()){
                throw new UserAlreadyExist("User already exist");
            }
            
            // Check if email is already used by a user
            if(userRepository.findByEmail(request.getEmail()).isPresent()) {
                throw new UserAlreadyExist("Email is already registered as a user");
            }

            Admin admin = new Admin();
            admin.setEmail(request.getEmail());
            admin.setFullName(request.getFullName());
            admin.setPassword(passwordEncoder.encode(request.getPassword()));
            admin.setPhone(request.getPhone());
            admin.setAddress(request.getAddress());
            adminRepository.save(admin);

            return new AdminSignUpResponse("Registered successfully");
    }

    @Override
    public AdminLoginResponse adminLogin(AdminLoginRequest request) {

        if(adminRepository.findByEmail(request.getEmail()).isEmpty()){
            throw new IncorrectLogin("Invalid email or password");
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        String token = jwtService.generateToken((UserDetails) authentication.getPrincipal());

        return new AdminLoginResponse(token);
    }

    @Override
    public List<UserResponse> viewUsers() {
        return userRepository.findAll().stream().map(user -> new UserResponse(user.getId(), user.getFullName(), user.getEmail())).toList();
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