package ru.otus.spring06.repository;

import ru.otus.spring06.exceptions.BookExistException;
import ru.otus.spring06.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    void insert(Book Book) throws BookExistException;
    void update(Book Book) throws BookExistException;
    void delete(Book Book) throws BookExistException;
    Optional<Book> getByID(Long bookID);
    List<Book> findAll();
}
