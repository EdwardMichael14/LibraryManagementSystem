package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dtos.requests.AddBookRequest;
import org.example.dtos.requests.UserSignUpRequest;
import org.example.dtos.responses.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.services.AdminServices;



@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminServices adminServices;

    @PostMapping("/register-user")
        public ResponseEntity<?> registerUser(@RequestBody UserSignUpRequest request) {
            return new ResponseEntity<>(new APIResponse(true, adminServices.registerUser(request)), HttpStatus.CREATED);
    }

    @GetMapping("/view-users")
    public ResponseEntity<?> viewUsers() {
        return ResponseEntity.ok(new APIResponse(true, adminServices.viewUsers()));
    }

    @GetMapping("/borrowed-books")
    public ResponseEntity<?> viewBorrowedBooks() {
        return new ResponseEntity<>(new APIResponse(true, adminServices.viewBorrowedBooks()), HttpStatus.OK);
    }

    @GetMapping("/all-books")
    public ResponseEntity<?> viewAllBooks() {
        return new ResponseEntity<>(new APIResponse(true, adminServices.viewAllBooks()), HttpStatus.OK);
    }

    @PostMapping("/add-book")
    public ResponseEntity<?> addBook(@RequestBody AddBookRequest request) {
        return new ResponseEntity<>(new APIResponse(true, adminServices.addBook(request)), HttpStatus.CREATED);
    }

}
