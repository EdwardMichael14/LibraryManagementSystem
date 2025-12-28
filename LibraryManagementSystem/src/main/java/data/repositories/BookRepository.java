package data.repositories;

import data.models.Book;
import data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
    Book findByTitleAuthorEdition(String title, String author, String edition);
    User findByEmail(String email);
}
