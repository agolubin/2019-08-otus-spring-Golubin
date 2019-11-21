package ru.otus.spring05.dao;

import ru.otus.spring05.Exceptions.BookExistException;
import ru.otus.spring05.domain.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {

    Book insert(Book book) throws BookExistException;
    void update(Book book);
    void deleteByID(Long bookID);
    int countByID(Long bookID);
    Book getBookByID(Long bookID);
    List <Book> findAll();
    Book getBookByParam(Long authorID, Long genreID, String name);
    int countByParam(Long authorID, Long genreID, String name);
}
