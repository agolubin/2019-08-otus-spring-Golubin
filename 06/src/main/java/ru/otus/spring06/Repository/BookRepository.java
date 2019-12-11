package ru.otus.spring06.Repository;

import ru.otus.spring06.Exceptions.AuthorExistException;
import ru.otus.spring06.Exceptions.BookExistException;
import ru.otus.spring06.domain.Author;
import ru.otus.spring06.domain.Book;

import java.util.List;

public interface BookRepository {

    void insert(Book Book) throws BookExistException;
    void update(Book Book) throws BookExistException;
    void delete(Book Book) throws BookExistException;
    Book getByID(Long bookID);
    List<Book> findAll();
}
