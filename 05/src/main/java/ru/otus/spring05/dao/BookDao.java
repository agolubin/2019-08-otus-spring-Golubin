package ru.otus.spring05.dao;

import ru.otus.spring05.domain.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {

    Book insert(Book book) throws SQLException;
    void update(Book book);
    void deleteByID(int bookID);
    int checkByID(int bookID);
    List findAll();
//    Book getByID(int bookID);
    Book getBookByBook(Book book);
    int countByName(Book author);
}
