package utils;

import data.models.Book;
import data.models.Borrow;
import data.models.User;
import dtos.requests.AddBookRequest;
import dtos.requests.UserSignUpRequest;
import dtos.responses.BorrowBookResponse;

public class Mapper {

    public static User mapSignUpUser(UserSignUpRequest request) {
            User user = new User();

        user.setFullName(request.getFullName());
        user.setUserEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());

        return user;
    }

    public static BorrowBookResponse mapBorrowBookResponse(Borrow borrow) {

        BorrowBookResponse borrowBookResponse = new BorrowBookResponse();

        borrowBookResponse.setUserId(borrow.getUser().getId());
        borrowBookResponse.setBookAuthor(borrow.getBook().getAuthor());
        borrowBookResponse.setBookTitle(borrow.getBook().getTitle());
        borrowBookResponse.setEdition(borrow.getBook().getEdition());
        borrowBookResponse.setMessage("Book borrowed");
        borrowBookResponse.getBorrowDate();


        return borrowBookResponse;
    }

    public static Book mapAddBook(AddBookRequest request) {

        Book book = new Book();

        book.setAuthor(request.getBook().getAuthor());
        book.setTitle(request.getBook().getTitle());
        book.setEdition(request.getBook().getEdition());
        book.setNoOfCopies(request.getBook().getNoOfCopies());
        book.setQuantity(request.getBook().getQuantity());
        book.setIsbn(request.getBook().getIsbn());

        return book;
    }




}
