package org.example.data.repositories;

import org.example.data.models.Book;
import org.example.data.models.Borrow;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends MongoRepository<Borrow, String> {

    Borrow findByEmailAndBookId(String email, String bookId);

    List<Borrow> findByEmail(String email);
}
