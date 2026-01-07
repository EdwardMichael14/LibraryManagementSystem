package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dtos.requests.BorrowBookRequest;
import org.example.dtos.requests.ReturnBookRequest;
import org.example.dtos.requests.UserLoginRequest;
import org.example.dtos.requests.UserSignUpRequest;
import org.example.dtos.responses.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.services.UserServices;
import org.example.services.UserServicesImpl;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServices userServices;

    @PostMapping("/borrow-book")
    public ResponseEntity<?> borrowBook(@RequestBody BorrowBookRequest request) {
        return new ResponseEntity<>(new APIResponse(true, userServices.borrowBook(request)), HttpStatus.OK);
    }

    @GetMapping("/view-borrowed-books")
    public ResponseEntity<?> viewBorrowedBooks() {
        return new ResponseEntity<>(new APIResponse(true, userServices.viewBorrowedBooks()), HttpStatus.OK);
    }

    @GetMapping("/view-books")
    public ResponseEntity<?> viewAllBooks() {
        return new ResponseEntity<>(new APIResponse(true, userServices.viewAllBooks()), HttpStatus.OK);
    }

    @PostMapping("/Return-book")
    public ResponseEntity<?> returnBook(@RequestBody ReturnBookRequest request) {
        return new ResponseEntity<>(new APIResponse(true, userServices.returnBook(request)), HttpStatus.OK);
    }

}
