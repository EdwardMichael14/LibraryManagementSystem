package controllers;

import dtos.requests.AddBookRequest;
import dtos.requests.AdminLoginRequest;
import dtos.requests.AdminSignUpRequest;
import dtos.requests.UserSignUpRequest;
import dtos.responses.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.AdminServices;
import services.AdminServicesImpl;


@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminServices adminServices = new AdminServicesImpl();

    @PostMapping("/sign up")
   public ResponseEntity<?> adminSignUp(@RequestBody AdminSignUpRequest request) {
        return new ResponseEntity<>(new APIResponse(true, adminServices.AdminSignUp(request)), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> adminLogin(@RequestBody AdminLoginRequest request) {
        return new ResponseEntity<>(new APIResponse(true, adminServices.AdminLogin(request)), HttpStatus.OK);
    }

    @PostMapping("/register user")
        public ResponseEntity<?> registerUser(@RequestBody UserSignUpRequest request) {
            return new ResponseEntity<>(new APIResponse(true, adminServices.registerUser(request)), HttpStatus.CREATED);
    }

    @GetMapping("/view users")
    public ResponseEntity<?> viewUsers() {
        return new ResponseEntity<>(new APIResponse(true, adminServices.viewUsers()), HttpStatus.OK);
    }

    @GetMapping("/borrowed books")
    public ResponseEntity<?> viewBorrowedBooks() {
        return new ResponseEntity<>(new APIResponse(true, adminServices.viewBorrowedBooks()), HttpStatus.OK);
    }

    @GetMapping("/all books")
    public ResponseEntity<?> viewAllBooks() {
        return new ResponseEntity<>(new APIResponse(true, adminServices.viewAllBooks()), HttpStatus.OK);
    }

    @PostMapping("/add-book")
    public ResponseEntity<?> addBook(@RequestBody AddBookRequest request) {
        return new ResponseEntity<>(new APIResponse(true, adminServices.addBook(request)), HttpStatus.CREATED);
    }

}
