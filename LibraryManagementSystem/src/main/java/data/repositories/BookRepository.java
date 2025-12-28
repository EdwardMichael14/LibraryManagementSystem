package data.repositories;

import data.models.Book;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
    Book findByTitleAuthorEdition(String title, String author, String edition);

}
