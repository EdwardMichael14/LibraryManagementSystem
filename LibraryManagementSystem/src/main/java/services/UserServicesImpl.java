package services;

import data.models.Book;
import data.models.BookStatus;
import data.models.Borrow;
import data.models.User;
import data.repositories.BookRepository;
import data.repositories.BorrowRepository;
import data.repositories.UserRepository;
import dtos.requests.BorrowBookRequest;
import dtos.requests.ReturnBookRequest;
import dtos.requests.UserLoginRequest;
import dtos.requests.UserSignUpRequest;
import dtos.responses.BorrowBookResponse;
import dtos.responses.ReturnBookResponse;
import dtos.responses.UserLoginResponse;
import dtos.responses.UserSignUpResponse;
import exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

import static utils.Mapper.mapBorrowBookResponse;
import static utils.Mapper.mapSignUpUser;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BorrowRepository borrowRepository;

    @Override
    public UserSignUpResponse signUp(UserSignUpRequest request) {

        if (userRepository.existsById(request.getEmail())) {
            throw new UserAlreadyExist("Email already exist");
        }

        User user = mapSignUpUser(request);
        userRepository.save(user);

        return new UserSignUpResponse("User signed up successfully");
    }

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        User user = (userRepository.findByEmail(request.getEmail()));

        if (user == null) {
            throw new IncorrectLogin("incorrect email or password");
        }

        if (!user.getPassword().equals(request.getPassword())) {
            throw new IncorrectLogin("incorrect password");
        }
        return new UserLoginResponse("Login Successful");
    }

    @Override
    public List<Book> viewAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public BorrowBookResponse borrowBook(String email,BorrowBookRequest request) {

        User user = userRepository.findByEmail(email);
            if(user == null) {
                throw new UserNotFound("user does not exist");
            }
        Book book = bookRepository.findByTitleAuthorEdition(request.getTitle(), request.getAuthor(), request.getEdition());
            if(book == null) {
                throw new BookNotFound("Book not found");
            }

        if(book.getNoOfCopies() == 0){
            book.setStatus(BookStatus.UN_AVAILABLE);
            throw new NoCopiesAvailable("No available copies");
        }

        Borrow borrow = new Borrow();
        borrow.setBook(book);
        borrow.setUser(user);
        borrow.setBorrowDate(LocalDateTime.now());

        Borrow isBorrowed = borrowRepository.findByUserEmailAndBook(email, book);
        if(isBorrowed != null) {
            throw new BookAlreadyBorrowed("Book already borrowed by you");
        }
        borrowRepository.save(borrow);

        book.setNoOfCopies(book.getNoOfCopies() - 1);
        bookRepository.save(book);

        user.getBorrowedBooks().add(borrow);
        userRepository.save(user);

        return mapBorrowBookResponse(borrow);

    }

    @Override
    public ReturnBookResponse returnBook(String email, ReturnBookRequest request) {

//        User user =  userRepository.findByEmail(request.getUser().getEmail());
//        if(user == null) {
//            throw new UserNotFound("user does not exist");
//        }
//
//        Book book = bookRepository.findByTitleAuthorEdition(request.getTitle(), request.getAuthor(), request.getEdition());
//        if(book == null) {
//            throw new BookNotFound("Book not found");
//        }
        Borrow borrow = borrowRepository.findByUserEmailAndBook(email, request.getBook());
        if(borrow == null) {
            throw new BookNotBorrowed("This book is not borrowed by you");
        }
        borrowRepository.delete(borrow);
        request.getBook().setNoOfCopies(request.getBook().getNoOfCopies() + 1);
        if(request.getBook().getNoOfCopies() > 0){
            request.getBook().setStatus(BookStatus.AVAILABLE);
        }
        bookRepository.save(request.getBook());

        User user = userRepository.findByEmail(email);
        user.getBorrowedBooks().remove(borrow);
        userRepository.save(user);

        ReturnBookResponse returnBookResponse = new ReturnBookResponse();

        returnBookResponse.setBook(request.getBook());
        returnBookResponse.setMessage("Book Returned");

        return returnBookResponse;
    }

    @Override
    public List<Borrow> viewBorrowedBooks(String email) {
        User user = userRepository.findByEmail(email);
        return user.getBorrowedBooks();
    }

//    private void findUserAndBook() {
//        User user =  userRepository.findByEmail(request.getUser().getEmail());
//        if(user == null) {
//            throw new UserNotFound("user does not exist");
//        }
//
//        Book book = bookRepository.findByTitleAuthorEdition(request.getTitle(), request.getAuthor(), request.getEdition());
//        if(book == null) {
//            throw new BookNotFound("Book not found");
//        }
//    }
}


