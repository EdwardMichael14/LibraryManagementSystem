package org.example.data.repositories;

import org.example.data.models.Book;
import org.example.data.models.Borrow;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepository extends MongoRepository<Borrow, String> {

    Borrow findByUser_EmailAndBook(String email, Book book);
}
