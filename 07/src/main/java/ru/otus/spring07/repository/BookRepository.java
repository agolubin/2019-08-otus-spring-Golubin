package ru.otus.spring07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.spring07.domain.Comments;
import ru.otus.spring07.exceptions.BookExistException;
import ru.otus.spring07.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book save(Book Book);
    void delete(Book Book);
    Optional<Book> findById(Long bookID);
    @Query("select b from Book b")
    List<Book> findAll();
}
