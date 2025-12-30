package controllers;

import dtos.requests.BorrowBookRequest;
import dtos.requests.ReturnBookRequest;
import dtos.requests.UserLoginRequest;
import dtos.requests.UserSignUpRequest;
import dtos.responses.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.UserServices;
import services.UserServicesImpl;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServices userServices = new UserServicesImpl();

    @PostMapping("/register")
    public ResponseEntity<?> signUp(@RequestBody UserSignUpRequest request) {
        return new ResponseEntity<>(new APIResponse(true, userServices.signUp(request)), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {
        return new ResponseEntity<>(new APIResponse(true, userServices.login(request)), HttpStatus.OK);
    }

    @PostMapping("/borrow-book")
    public ResponseEntity<?> borrowBook(@RequestHeader("userEmail") String email, @RequestBody BorrowBookRequest request) {
        return new ResponseEntity<>(new APIResponse(true, userServices.borrowBook(email, request)), HttpStatus.OK);
    }

    @GetMapping("/view-borrowed-books")
    public ResponseEntity<?> viewBorrowedBooks(@RequestHeader("userEmail") String email) {
        return new ResponseEntity<>(new APIResponse(true, userServices.viewBorrowedBooks(email)), HttpStatus.OK);
    }

    @GetMapping("/view-books")
    public ResponseEntity<?> viewAllBooks() {
        return new ResponseEntity<>(new APIResponse(true, userServices.viewAllBooks()), HttpStatus.OK);
    }

    @PostMapping("/Return-book")
    public ResponseEntity<?> returnBook(@RequestHeader("email") String email, @RequestBody ReturnBookRequest request) {
        return new ResponseEntity<>(new APIResponse(true, userServices.returnBook(email,request)), HttpStatus.OK);
    }

}
