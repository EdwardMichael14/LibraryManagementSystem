package data.repositories;

import data.models.Book;
import data.models.Borrow;
import data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BorrowRepository extends MongoRepository<Borrow, String> {

    Borrow findByUserAndBook(User user, Book book);
}
