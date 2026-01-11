package org.example.services;

import org.example.data.models.*;
import org.example.data.repositories.BookRepository;
import org.example.data.repositories.BorrowRepository;
import org.example.data.repositories.UserRepository;
import org.example.dtos.requests.BorrowBookRequest;
import org.example.dtos.requests.ReturnBookRequest;
import org.example.dtos.requests.UserLoginRequest;
import org.example.dtos.requests.UserSignUpRequest;
import org.example.dtos.responses.*;
import org.example.exceptions.*;
import org.example.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.example.utils.Mapper.mapBorrowBookResponse;
import static org.example.utils.Mapper.mapSignUpUser;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @Override
    public UserSignUpResponse signUp(UserSignUpRequest request) {

        if(userRepository.findByEmail(request.getEmail()).isPresent()){
                throw new UserAlreadyExist("User already exist");
        }

        User user = mapSignUpUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return new UserSignUpResponse("User signed up successfully");
    }

    @Override
    public UserLoginResponse login(UserLoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        String token = jwtService.generateToken((UserDetails) authentication.getPrincipal());

        return new UserLoginResponse(token);
    }

   @Override
    public List<Book> viewAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public BorrowBookResponse borrowBook(BorrowBookRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            throw new RuntimeException("User not authenticated");
        }
        String email = authentication.getName();


            String title = request.getTitle().trim();
            String edition = request.getEdition();
            String author = request.getAuthor();
        Book book = bookRepository.findByTitleAndAuthorAndEdition(title, author, edition);
            if(book == null) {
                throw new BookNotFound("Book not found");
            }

        if(book.getNoOfCopies() == 0){
            book.setStatus(BookStatus.UN_AVAILABLE);
            throw new NoCopiesAvailable("No available copies");
        }

        Borrow isBorrowed = borrowRepository.findByUser_EmailAndBook_Id(email, book.getId());
        if(isBorrowed != null) {
            throw new BookAlreadyBorrowed("Book already borrowed by you");
        }

        Borrow borrow = new Borrow();
        borrow.setBookId(book.getId());
        borrow.setEmail(email);
        borrow.setBorrowDate(LocalDateTime.now());

        borrowRepository.save(borrow);

        book.setNoOfCopies(book.getNoOfCopies() - 1);
        if(book.getNoOfCopies() == 0){
            book.setStatus(BookStatus.UN_AVAILABLE);
        }
        bookRepository.save(book);

        return mapBorrowBookResponse(borrow);

    }

    @Override
    public ReturnBookResponse returnBook(ReturnBookRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Borrow borrow = borrowRepository.findByUser_EmailAndBook_Id(email, request.getBook().getId());
        if(borrow == null) {
            throw new BookNotBorrowed("This book is not borrowed by you");
        }
        borrowRepository.delete(borrow);
        request.getBook().setNoOfCopies(request.getBook().getNoOfCopies() + 1);
        request.getBook().setStatus(BookStatus.AVAILABLE);

        bookRepository.save(request.getBook());

        ReturnBookResponse returnBookResponse = new ReturnBookResponse();

        returnBookResponse.setBook(request.getBook());
        returnBookResponse.setMessage("Book Returned");

        return returnBookResponse;
    }

    @Override
    public List<Book> viewBorrowedBooks() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        List<Borrow> borrows = borrowRepository.findByEmail(email);
        List<Book> borrowedBoooks = new ArrayList<>();
        for (Borrow borrow : borrows) {
            bookRepository.findById(borrow.getBookId()).ifPresent(borrowedBoooks::add);

        }
        return borrowedBoooks;
    }

}

