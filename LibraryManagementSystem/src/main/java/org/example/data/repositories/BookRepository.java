package org.example.data.repositories;

import org.example.data.models.Book;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    Book findByTitleAndAuthorAndEdition(String title, String author, String edition);

}
